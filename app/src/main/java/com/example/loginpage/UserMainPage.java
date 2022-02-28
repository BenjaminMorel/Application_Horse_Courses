package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);
    }

    public void editProfile(View view){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    public void purchaseCourse(View view){
        Intent intent = new Intent(this, PurchaseCourse.class);
        startActivity(intent);
    }


}