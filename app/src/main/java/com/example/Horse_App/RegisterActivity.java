package com.example.Horse_App;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginpage.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEmail, etPhoneNumber, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeForm();
    }

    private void initializeForm() {
        etFirstName = findViewById(R.id.first_name_register);
        etLastName = findViewById(R.id.last_name_register);
        etPhoneNumber = findViewById(R.id.phone_number_register);
        etEmail = findViewById(R.id.email_register);
        etPassword = findViewById(R.id.password_register);
        etConfirmPassword = findViewById(R.id.confirm_password_register);

        Button saveBtn = findViewById(R.id.register_button);
        saveBtn.setOnClickListener(view -> createAccount(
                        etFirstName.getText().toString(),
                        etLastName.getText().toString(),
                        etPhoneNumber.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString(),
                        etConfirmPassword.getText().toString()
                        ));


    }

    private void createAccount(String firstName, String lastName, String PhoneNumber, String Email, String Password, String ConfirmPassword) {



    }


}