package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.repository.UserRepository;
import com.example.Horse_App.R;

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

        // Set up login form
        emailView = findViewById(R.id.email_login);
        passwordView = findViewById(R.id.password_login);

        // Add the login button with listener
        Button logInButton = findViewById(R.id.button_login);
        logInButton.setOnClickListener(view -> attemptLogin());


        // Add the register button with listener
        Button registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(view -> startActivity(new Intent(Login.this, Register.class)));
    }


    /**
     * Method to verify the credentials that have been given
     * If the are correct the main page will be load and the user id stocked on
     * the shared preference, if not the errorMessage is display and the page stay the same
     */
    private void attemptLogin() {

        boolean cancel = false;
        View focusView = null;
        // Reset errors
        emailView.setError(null);
        passwordView.setError(null);

        // Store values for the login attempt
        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        // Check for a valid email address
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.wrong_credentials));
            focusView = emailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();

        } else {
            repository.signIn(email, password, task -> {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(Login.this, MainPage.class);
                    startActivity(intent);
                    emailView.setText("");
                    passwordView.setText("");
                } else {
                    emailView.setError(getString(R.string.wrong_credentials));
                    emailView.requestFocus();
                    passwordView.setText("");
                }
            });
        }
    }
    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}