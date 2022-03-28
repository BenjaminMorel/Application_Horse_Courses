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


    /**
     *
     * method to display the page and get the reference of the textView
     * we also create the onClickListener on the register button
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

    /**
     * Method to verify the credentails that have been given
     * If the are correct the main page will be load and the user id stocked on
     * the shared preference, if not the errorMessage is display and the page stay the same
     */
    private void attemptLogin() {

        boolean cancel = false;
        emailView.setError(null);
        passwordView.setError(null);

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            emailView.setError(getString(R.string.field_required));
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
}