package com.example.Horse_App.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.CustomCourseItem;
import com.example.Horse_App.Database.repository.RideRepository;
import com.example.Horse_App.R;

public class MainPage extends AppCompatActivity {

    private RideRepository repository;
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



        ListView listView = findViewById(R.id.ListRideToChoose);
        repository.getRides(getApplication()).observe(this, ridesEntities -> {
            if(ridesEntities != null){

                    CustomCourseItem customCourseItem = new CustomCourseItem(this, ridesEntities);
                    listView.setAdapter(customCourseItem);


            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}