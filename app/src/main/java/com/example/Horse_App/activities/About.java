package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.Horse_App.R;

/**
 * About page, that shows information about the app and the developers
 */
public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}