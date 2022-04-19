package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.UserEntity;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.repository.UserRepository;
import com.example.Horse_App.R;
import com.google.firebase.auth.FirebaseAuth;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class EditAccount extends AppCompatActivity {

    private static final String TAG = "EditAccount activity";
    private String userID;
    private UserRepository userRepository;
    private Switch toggleButton;

    EditText edFirstname, edLastname, edPhoneNumber, edEmail;
    EditText edOldPassword, edPassword, edConfirmPassword;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        userRepository = ((BaseApp) getApplication()).getUserRepository();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        toggleButton = findViewById(R.id.switchDarkMode);

        // Get the user ID
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Get the preference for dark mode
        userRepository.getUserByID(userID).observe(this, userEntity -> {
            setDarkMode(userEntity.isDarkMode());
        });

        displayUserInfo();
    }

    private void setDarkMode(boolean isDarkMode) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            toggleButton.setText(R.string.disable_darkMode);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            toggleButton.setText(R.string.enable_darkMode);
        }
    }

    private void displayUserInfo() {

        edFirstname = findViewById(R.id.first_name_edit);
        edLastname = findViewById(R.id.last_name_edit);
        edPhoneNumber = findViewById(R.id.phone_number_edit);
        edEmail = findViewById(R.id.email_edit);

        edOldPassword = findViewById(R.id.old_password_edit);
        edPassword = findViewById(R.id.password_edit);
        edConfirmPassword = findViewById(R.id.confirm_password_edit);

        // Get the user ID
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        userRepository.getUserByID(userID).observe(EditAccount.this, user -> {
            edEmail.setText(user.email);
            edFirstname.setText(user.firstName);
            edLastname.setText(user.lastName);
            edPhoneNumber.setText(user.phoneNumber);
        });

        Button saveModificationButton = findViewById(R.id.button_save_changes);
        saveModificationButton.setOnClickListener(view -> saveModification(edFirstname.getText().toString(), edLastname.getText().toString(),
                edPhoneNumber.getText().toString(), edEmail.getText().toString(),
                edOldPassword.getText().toString(), edPassword.getText().toString(), edConfirmPassword.getText().toString()));
    }

    private void saveModification(String firstname, String lastname, String phonenumber, String email, String oldPassword, String password, String confirmPassword) {
        boolean cancel = false;
        String newPassword = "";

        if (oldPassword.isEmpty()) {
            cancel = true;
            edOldPassword.setError(getString(R.string.field_required));
        }

        if (password.isEmpty() && !confirmPassword.isEmpty()) {
            newPassword = oldPassword;
            cancel = true;
            edPassword.setError(getString(R.string.field_required));
        }
        if (confirmPassword.isEmpty() && !password.isEmpty()) {
            cancel = true;
            edConfirmPassword.setError(getString(R.string.field_required));
        }

        if (!confirmPassword.equals(password)){
            cancel = true;
            edPassword.setError(getString(R.string.no_matching_pwd));
            edConfirmPassword.setError(getString(R.string.no_matching_pwd));
        }

        else {
            newPassword = password;
        }

        if (!cancel) {
            String finalNewPassword = newPassword;
            userRepository.getUserByID(userID).observe(EditAccount.this, user -> {
                UserEntity editUser = new UserEntity(email, finalNewPassword, firstname, lastname, phonenumber, toggleButton.isChecked());
                editUser.setUserID(userID);
                userRepository.update(editUser, new OnAsyncEventListener() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "Edit user: success");
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Log.d(TAG, "Edit user: failure", e);
                        }
                    });
                    Intent intent = new Intent(this, MainPage.class);
                    startActivity(intent);
            });
        }
    }
}