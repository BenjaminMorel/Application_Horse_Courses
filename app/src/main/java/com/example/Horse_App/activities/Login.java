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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {

    private EditText emailView, passwordView;
    private UserRepository repository;
    private User newUser;

    private static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logout();
        reinitializeDatabase();

        repository = ((BaseApp) getApplicationContext()).getUserRepository();

        emailView = findViewById(R.id.email_login);
        passwordView = findViewById(R.id.password_login);

        // Add the register button with listener
        Button registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
                //reinitializeDatabase();
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
            //    repository.getUserByEmail(email, getApplication()).observe(Login.this, user -> setUserValue(user));
            repository.getUserByEmail(email, getApplication()).observe(Login.this, user -> {
                if (user != null) {
                    if (user.getPassword().equals(password)) {
                        SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_USERID, 0).edit();
                        editor.putInt(BaseActivity.PREFS_USERID, user.userID);
                        editor.apply();
                        Intent intent = new Intent(this, MainPage.class);
                        startActivity(intent);

                    }
                } else {
                    emailView.setError(getString(R.string.error_login_message));
                    passwordView.setError(getString(R.string.error_login_message));
                }
            });
        }
    }

    private void reinitializeDatabase() {
        DatabaseInitializer.populateDatabase(AppDatabase.getAppDateBase(this));
    }

    private void sendSMS() {
        Intent intent = new Intent(getApplicationContext(), MainPage.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("000", null, "you access code is 4444", pi, null);
    }

    /**
     * Method to clear the prefs id of the user and redirect to login page
     */
    private void logout() {
        SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_USERID, 0).edit();
        editor.remove(BaseActivity.PREFS_USERID);
        editor.apply();
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
