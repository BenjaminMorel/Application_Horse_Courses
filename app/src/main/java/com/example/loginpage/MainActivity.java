package com.example.loginpage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends FragmentActivity {

    private boolean IsLoginPageActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_);
    }

    public void switchFragment(View view){

        if(IsLoginPageActive) {
            setContentView(R.layout.fragment_register);
            IsLoginPageActive=false;
        }else{
            setContentView(R.layout.fragment_login_);
            IsLoginPageActive=true;
        }
    }


    public void logIn(View view){

        EditText editEmail = findViewById(R.id.email_login);
        EditText editPassword = findViewById(R.id.password_login);

        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();


        if(email.equals("admin") && password.equals("123")){
            Intent intent = new Intent(this, UserMainPage.class);
            startActivity(intent);
        }else{
            editEmail.setError("Wrong Credentials");
            editPassword.setError("Wrong Credentials");
        }
    }
}