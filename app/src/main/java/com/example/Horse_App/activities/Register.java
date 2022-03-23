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

    private void registerNewUser(String firstname, String lastname, String phoneNumber, String email, String password) {

        String encryptedPwd = Encrypt.md5(password);
        User newUser = new User(email, encryptedPwd, firstname, lastname, phoneNumber);

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
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}