package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.AppDatabase;
import com.example.Horse_App.Database.DatabaseInitializer;
import com.example.Horse_App.Database.Entity.User;
import com.example.Horse_App.Database.repository.UserRepository;
import com.example.Horse_App.R;
import com.example.Horse_App.encryption.Encrypt;

public class Login extends AppCompatActivity {

    private EditText emailView, passwordView;
    private UserRepository repository;
    private User newUser;

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        SharedPreferences preferences = getSharedPreferences(BaseActivity.PREFS_LOGGED, 0);
//        int userID = preferences.getInt(BaseActivity.PREFS_USERID, 1);

//        if(userID > 0){
//            Intent intent = new Intent(this, MainPage.class);
//            startActivity(intent);
//            finish();
//        }

        repository = ((BaseApp) getApplicationContext()).getUserRepository();

        emailView = findViewById(R.id.email_login);
        passwordView = findViewById(R.id.password_login);

        Button registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        // Add the login button with listener
        Button logInButton = findViewById(R.id.button_login);
        logInButton.setOnClickListener(view -> attemptLogin());

    }

    /**
     * Method called by the register button to redirect to register page with an intent
     */
    private void register() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    private void attemptLogin() {

        boolean cancel = false;
        emailView.setError(null);
        passwordView.setError(null);

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        if (email.isEmpty()) {
            emailView.setError(getString(R.string.field_required));
            cancel = true;
        } else if (password.isEmpty()) {
            passwordView.setError(getString(R.string.field_required));
            cancel = true;
        }

        if (!cancel) {
            repository.getUserByEmail(email, getApplication()).observe(Login.this, user -> {
                if (user != null) {
                    String encryptedPwd = Encrypt.md5(password);
                    if (user.getPassword().equals(encryptedPwd)) {
                        SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_LOGGED, 0).edit();
                        editor.putInt(BaseActivity.PREFS_USERID, user.userID);
                        System.out.println(user.userID);
                        editor.apply();
                        Intent intent = new Intent(this, MainPage.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    emailView.setError(getString(R.string.error_login_message));
                    passwordView.setError(getString(R.string.error_login_message));
                }
            });
        }
    }


    /**
     * Method to clear the prefs id of the user and redirect to login page
     */
//    private void logout() {
//        SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_LOGGED, 0).edit();
//        editor.putInt(BaseActivity.PREFS_USERID, -1);
//        editor.apply();
//    }

}
