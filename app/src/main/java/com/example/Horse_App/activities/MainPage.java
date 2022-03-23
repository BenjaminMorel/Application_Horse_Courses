package com.example.Horse_App.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.ArrayAdapter.CustomRideItem;
import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.Database.repository.RideRepository;
import com.example.Horse_App.R;

import java.util.List;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(false);

        startMainPage();
    }

    private void startMainPage() {

        RideRepository repository = ((BaseApp) getApplication()).getRideRepository();

        List<Ride> rides = repository.getRides(getApplication());

        CustomRideItem customCourseItem = new CustomRideItem(this, rides);
        customCourseItem.setMainPage(this);
        ListView listView = findViewById(R.id.ListRideToChoose);
        listView.setAdapter(customCourseItem);

        Button showCourses = findViewById(R.id.showMyCourse);
        showCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateAllCoursesPage();
            }
        });
    }

    /**
     * Inflate the menu and adds items to the action bar if it is present.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Manages the dropdown menu in the toolbar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_edit_profile) {
            Intent intent = new Intent(this, EditAccount.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.menu_disconnect) {
            logout();
        }
        if (item.getItemId() == R.id.menu_about) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void generateCreateCoursePage(int rideID) {

        // We used the position of the button that was pressed to stored the ride ID in shared Preferences to retreive it later
        SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_RIDEID, 0).edit();
        editor.putInt(BaseActivity.PREFS_USERID, rideID);
        editor.apply();
        Intent intent = new Intent(this, CreateNewCourse.class);
        startActivity(intent);
    }

    public void generateAllCoursesPage() {
        Intent intent = new Intent(this, DisplayAllCourses.class);
        startActivity(intent);
    }

    public void logout() {
        SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_USERID, 0).edit();
        editor.remove(BaseActivity.PREFS_USERID);
        editor.apply();

        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}