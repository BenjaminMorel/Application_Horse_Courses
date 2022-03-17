package com.example.Horse_App.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.CustomCourseItem;
import com.example.Horse_App.Database.repository.RideRepository;
import com.example.Horse_App.R;

import java.util.List;

public class MainPage extends AppCompatActivity {

    private RideRepository repository;
    private List rides;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        startMainPage();
    }

    private void startMainPage(){

        repository = ((BaseApp) getApplication()).getRideRepository();

        rides = repository.getRides(getApplication());

        CustomCourseItem customCourseItem = new CustomCourseItem(this, rides);
        customCourseItem.setMainPage(this);
        ListView listView = findViewById(R.id.ListRideToChoose);
        listView.setAdapter(customCourseItem);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void generateCreateCoursePage(int rideID){

        // We used the position of the button that was pressed to stored the ride ID in shared Preferences to retreive it later
        SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_RIDEID, 0).edit();
        editor.putInt(BaseActivity.PREFS_USERID, rideID);
        editor.apply();
        Intent intent = new Intent(this, CreateNewCourse.class);
      //  Intent intent = new Intent(this, EditAccount.class);
        startActivity(intent);
    }
}