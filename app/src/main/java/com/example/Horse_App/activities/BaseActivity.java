package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.Horse_App.R;

public class BaseActivity extends AppCompatActivity {

    public static final String PREFS_USERID = "LoggedIn";
    public static final String PREFS_RIDEID = "RideChoose";
    public static final String PREFS_LOGGED = "SharedPrefs_For_loggedUser";
    public static final String PREFS_RIDE = "SharedPrefs_For_Ride";
    public static final String PREFS_DARKMODE = "SharedPrefs_For_DarkMode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}