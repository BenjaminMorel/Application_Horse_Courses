package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterPage extends AppCompatActivity {

    public EditText firstname;
    public EditText lastname;
    public EditText npa;
    public EditText city;
    public EditText address;
    public EditText email;
    public EditText password;
    public EditText confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
    }


    public void registerInformation(View view ){

    }

    public boolean check_password(String password, String confirmPassword){
        if(password.equals(confirmPassword)){
            return true;
        }
        return false;
    }
}