package com.example.Horse_App.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.AppDatabase;
import com.example.Horse_App.Database.DatabaseInitializer;
import com.example.Horse_App.Database.repository.UserRepository;
import com.example.Horse_App.R;

public class Login extends AppCompatActivity {

    private EditText emailView;
    private EditText passwordView;

    private UserRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO mettre dan sles strins
        setTitle("Login");
        setContentView(R.layout.activity_login);

        repository = ((BaseApp) getApplication()).getUserRepository();

        emailView = findViewById(R.id.email_login);
        passwordView = findViewById(R.id.password_login);

        Button registerButton = findViewById(R.id.buttonSignup);
        registerButton.setOnClickListener(view -> startActivity(
                new Intent(this, Register.class)
        ));

        Button logInButton = findViewById(R.id.buttonLogin);
     //   logInButton.setOnClickListener(view -> attemptLogin());
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reinitializeDatabase();
            }
        });

    }

    private void attemptLogin(){

        boolean cancel = false;
        emailView.setError(null);
        passwordView.setError(null);

        String email = emailView.getText().toString();
        String password = emailView.getText().toString();


        if(email.isEmpty()){
            emailView.setError("This field is required");
            cancel = true;
        }else if(password.isEmpty()){
            passwordView.setError("This field is required");
            cancel = true;
        }

        if(!cancel){
            repository = ((BaseApp) getApplication()).getUserRepository();
        }
    }


    private void reinitializeDatabase() {
        DatabaseInitializer.populateDatabase(AppDatabase.getAppDateBase(this));
    }
}