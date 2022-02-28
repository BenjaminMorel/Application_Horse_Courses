package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadRegisterPage(View view){
        Intent intent = new Intent(this, RegisterPage.class);
        startActivity(intent);
    }

    public void logIn(View view){
        EditText editEmail = findViewById(R.id.email_login);
        EditText editPassword = findViewById(R.id.password_login);

        String email = editEmail.toString();
        String password = editPassword.toString();

        System.out.println("#############################################");
        System.out.println("email : " + email + " password : " + password);

        if(email.equals("admin") && password.equals("123")){
            Intent intent = new Intent(this, UserMainPage.class);
            startActivity(intent);
        }
    }
}