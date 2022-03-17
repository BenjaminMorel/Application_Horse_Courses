package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.User;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.async.User.UpdateUser;
import com.example.Horse_App.Database.repository.UserRepository;
import com.example.Horse_App.R;

public class EditAccount extends AppCompatActivity {

    private static final String TAG = "EditAccount activity";

    private int userID;

    private UserRepository userRepository;

    EditText edFirstname ;
    EditText edLastname ;
    EditText edPhoneNumber ;
    EditText edEmail ;

    EditText edOldPassword ;
    EditText edPassword ;
    EditText edConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        userRepository =  ((BaseApp) getApplicationContext()).getUserRepository();
        displayUserInfo();
    }


    private void displayUserInfo(){

         edFirstname = findViewById(R.id.first_name_edit);
         edLastname = findViewById(R.id.last_name_edit);
         edPhoneNumber = findViewById(R.id.phone_number_edit);
         edEmail = findViewById(R.id.email_edit);

         edOldPassword = findViewById(R.id.old_password_edit);
         edPassword = findViewById(R.id.password_edit);
         edConfirmPassword = findViewById(R.id.confirm_password_edit);

        SharedPreferences preferences = getSharedPreferences(BaseActivity.PREFS_USERID, 0);

        userID = preferences.getInt(BaseActivity.PREFS_USERID,1);

       userRepository.getUserByID(userID,getApplication()).observe(EditAccount.this, user -> {
           edFirstname.setText(user.firstName);
           edLastname.setText(user.lastName);
           edPhoneNumber.setText(user.phoneNumber);
           edEmail.setText(user.email);
       });

        Button saveModificationButton = findViewById(R.id.button_save_changes);
        saveModificationButton.setOnClickListener(view -> saveModification(edFirstname.getText().toString(), edLastname.getText().toString(),
                                                        edPhoneNumber.getText().toString(), edEmail.getText().toString(),
                                                        edOldPassword.getText().toString(), edPassword.getText().toString(), edConfirmPassword.getText().toString()));
    }

    private void saveModification(String firstname, String lastname, String phonenumber, String email, String oldPassword, String password, String confirmPassword) {

        boolean cancel = false;

        if (oldPassword.isEmpty()) {
            cancel = true;
            edOldPassword.setError(getString(R.string.FieldRequired));
        }
        if (password.isEmpty()) {
            cancel = true;
            edPassword.setError(getString(R.string.FieldRequired));
        }
        if (confirmPassword.isEmpty()) {
            cancel = true;
            edConfirmPassword.setError(getString(R.string.FieldRequired));
        }

        if (!cancel) {
            userRepository.getUserByID(userID, getApplication()).observe(EditAccount.this, user -> {

                //TODO ajouter le edit seulement pour la partie Info user sans les credentials
                if (user.getPassword().equals(oldPassword)) {
                    User editUser = new User(email, password, firstname, lastname, phonenumber);
                    editUser.setUserID(userID);
                    new UpdateUser(getApplication(), new OnAsyncEventListener() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "Edit user: success");
                        }
                        @Override
                        public void onFailure(Exception e) {
                            Log.d(TAG, "Edit user: failure", e); }
                    }).execute(editUser);
                    Intent intent = new Intent(this, MainPage.class);
                    startActivity(intent);
                }else{
                    edOldPassword.setError(getString(R.string.ErrorLoginMessage));
                }
            });

        }
    }
}