package com.example.Horse_App.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.AppDatabase;
import com.example.Horse_App.Database.DatabaseInitializer;
import com.example.Horse_App.Database.Entity.User;
import com.example.Horse_App.Database.repository.UserRepository;
import com.example.Horse_App.R;

public class Login extends AppCompatActivity {

    private EditText emailView;
    private EditText passwordView;

    private UserRepository repository;

    private User newUser;

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
                reinitializeDatabase();
            }
        });

        Button logInButton = findViewById(R.id.button_login);
        logInButton.setOnClickListener(view -> attemptLogin());


    }

    private void attemptLogin(){

        boolean cancel = false;
        emailView.setError(null);
        passwordView.setError(null);

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();


        if(email.isEmpty()){
            emailView.setError(getString(R.string.FieldRequired));
            cancel = true;
        }else if(password.isEmpty()){
            passwordView.setError(getString(R.string.FieldRequired));
            cancel = true;
        }

        if(!cancel){

        //    repository.getUserByEmail(email, getApplication()).observe(Login.this, user -> setUserValue(user));
            repository.getUserByEmail(email,getApplication()).observe(Login.this, user -> {
                if (user != null) {
                    if (user.getPassword().equals(password)) {
                        SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_ID, 0).edit();
                        editor.putInt(BaseActivity.PREFS_ID, user.userID);
                        editor.apply();
                       Intent intent = new Intent(this, MainPage.class);
                        startActivity(intent);

                    }
            }else {
                    emailView.setError(getString(R.string.ErrorLoginMessage));
                    passwordView.setError(getString(R.string.ErrorLoginMessage));
                }
            });

        }
    }

    private void reinitializeDatabase() {
        DatabaseInitializer.populateDatabase(AppDatabase.getAppDateBase(this));
    }

    private void setUserValue(User user){
        newUser = user;
    }

}
