package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.Horse_App.Database.Entity.User;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.async.User.CreateUser;
import com.example.Horse_App.R;
import com.example.Horse_App.encryption.Encrypt;

public class Register extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private EditText etFirstname, etLastname, etPhoneNumber, etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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

        Button registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(view -> registerNewUser(etFirstname.getText().toString(), etLastname.getText().toString(),
                etPhoneNumber.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString()));
    }

    /**
     * Method that will create a new user in our database, by getting all the elements,details that the user has given
     */
    private void registerNewUser(String firstname, String lastname, String phoneNumber, String email, String password) {

        //Encrypt the password by calling the md5 method in the Encrypt class
        String encryptedPwd = Encrypt.md5(password);
        User newUser = new User(email, encryptedPwd, firstname, lastname, phoneNumber);

        //Tries to create a new user
        new CreateUser(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "createUserWithEmail: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "createUserWithEmail: failure", e);
            }
        }).execute(newUser);

        //And finally launch a new activity with the Intent to return on the Login page
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}