package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.UserEntity;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.repository.UserRepository;
import com.example.Horse_App.R;

public class Register extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private UserRepository userRepository;

    private EditText etFirstname, etLastname, etPhoneNumber, etEmail, etPassword, etPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userRepository = ((BaseApp) getApplication()).getUserRepository();
        startPage();
    }

    /**
     * Method to load all the different parts of the page, with edit texts and button
     * and to create the references between variables and buttons
     */
    private void startPage() {
        etFirstname = findViewById(R.id.first_name_register);
        etLastname = findViewById(R.id.last_name_register);
        etPhoneNumber = findViewById(R.id.phone_number_register);
        etEmail = findViewById(R.id.email_register);
        etPassword = findViewById(R.id.password_register);
        etPassword2 = findViewById(R.id.confirm_password_register);

        Button registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(view -> registerNewUser(
                etFirstname.getText().toString(),
                etLastname.getText().toString(),
                etPhoneNumber.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                etPassword2.getText().toString()
        ));
    }

    /**
     * Method that will create a new user in our database, by getting all the elements,details that the user has given
     */
    private void registerNewUser(String firstname, String lastname, String phoneNumber, String email, String password, String password2) {
        if (!password.equals(password2) || password.length() < 5) {
            etPassword.setError(getString(R.string.no_matching_pwd));
            etPassword.requestFocus();
            etPassword.setText("");
            etPassword2.setText("");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Invalid login credentials");
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            etPhoneNumber.setError("Invalid phone number");
            etPhoneNumber.requestFocus();
            return;
        }

        UserEntity newUserEntity = new UserEntity(email, password, firstname, lastname, phoneNumber, false);

        //Tries to create a new user
        userRepository.register(newUserEntity, new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "createUserWithEmail: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "createUserWithEmail: failure", e);
            }
        });

        //And finally launch a new activity with the Intent to return on the Login page
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}