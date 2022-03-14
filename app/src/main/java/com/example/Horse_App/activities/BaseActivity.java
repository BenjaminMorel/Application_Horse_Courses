package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.Horse_App.R;

public class BaseActivity extends AppCompatActivity {

    public static final String PREFS_ID = "SharedPrefs";
    public static final String PREFS_USER = "LoggedIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


    }
}