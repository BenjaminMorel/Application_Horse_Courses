package com.example.Horse_App.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.Horse_App.ArrayAdapter.RideAdapter;
import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.AppDatabase;
import com.example.Horse_App.Database.DatabaseInitializer;
import com.example.Horse_App.Database.repository.CourseRepository;
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

        SharedPreferences preferences = getSharedPreferences(BaseActivity.PREFS_LOGGED, 0);
        int userID = preferences.getInt(BaseActivity.PREFS_USERID, 1);

        if (userID <= 0) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }

        setDarkMode();

        startMainPage();
    }

    private void startMainPage() {
        RideRepository repository = ((BaseApp) getApplication()).getRideRepository();
        List rides = repository.getRides(getApplication());
        if (rides.isEmpty()) {
            logout();
            reinitializeDatabase();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }
        RideAdapter rideAdapter = new RideAdapter(rides);
        rideAdapter.setMainPage(this);
        RecyclerView recyclerView = findViewById(R.id.ListRideToChoose);
        recyclerView.setAdapter(rideAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setDarkMode() {
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPrefs_For_DarkMode", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);

        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /**
     * Inflates the menu and add items to the toolbar
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Manages the dropdown menu in the toolbar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_edit_profile) {
            Intent intent = new Intent(this, EditAccount.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.menu_mycourses) {
            generateAllCoursesPage();
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
        SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_RIDE, 0).edit();
        editor.putInt(BaseActivity.PREFS_RIDEID, rideID);
        editor.apply();
        Intent intent = new Intent(this, CreateNewCourse.class);
        startActivity(intent);
    }

    public void generateAllCoursesPage() {
        Intent intent = new Intent(this, DisplayAllCourses.class);
        startActivity(intent);
    }

    public void logout() {
        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogCustom).create();
        alertDialog.setTitle("Disconnect");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Do you really want to disconnect from your account?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Disconnect", (dialog, which) -> {
            SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_LOGGED, 0).edit();
            editor.putInt(BaseActivity.PREFS_USERID, -1);
            editor.apply();
            Intent intent = new Intent(this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", (dialog, which) -> alertDialog.dismiss());
        alertDialog.show();

    }

    private void reinitializeDatabase() {
        DatabaseInitializer.populateDatabase(AppDatabase.getAppDateBase(this));
    }
}