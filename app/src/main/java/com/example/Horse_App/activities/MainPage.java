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
import com.example.Horse_App.ArrayAdapter.RideAdapter;
import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.repository.RideRepository;
import com.example.Horse_App.Database.repository.UserRepository;
import com.example.Horse_App.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainPage extends AppCompatActivity {

    private RideRepository rideRepository;
    private UserRepository userRepository;

    /**
     * When the page is create we first check if the SharedPreference link to the user id is null
     * if yes we close this activity and load the login page
     * if not we continue to load the main page
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        rideRepository = ((BaseApp) getApplication()).getRideRepository();
        userRepository = ((BaseApp) getApplication()).getUserRepository();

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(false);

        // Get user Id
        String userID = "";
        try {
            userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        } catch (Exception e){
            e.printStackTrace();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }

        // Get the preference for dark mode
        userRepository.getUserByID(userID).observe(this, userEntity -> {
            setDarkMode(userEntity.isDarkMode());
        });

        // Load main page
        startMainPage();
    }

    /**
     * Method to load and display all different Ride on a recyclerView
     * If the rides is empty it means that the database were not initialized
     * so we logout the user and generate the database correctly, you then will be able to
     * log with the default credential that are on the git
     * If the list is not empty we created a RideAdapter to put it on the recyclerView
     */
    private void startMainPage() {
        rideRepository.getAllRides().observe(this, rides ->{
            RideAdapter rideAdapter = new RideAdapter(rides);
            rideAdapter.setMainPage(this);
            RecyclerView recyclerView = findViewById(R.id.ListRideToChoose);
            recyclerView.setAdapter(rideAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        });
    }

    /**
     * Method to check if we need to use light or dark mode
     */
    private void setDarkMode(boolean isDarkModeOn) {
        if (isDarkModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /**
     * Inflates the menu and add items to the toolbar
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
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_edit_profile) {
            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            System.out.println("--------------------" + userID);
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


    /**
     * @param rideID id of the ride you chossed
     *               The page to create a new reservation is then loaded
     *               We don't finish the main page activity to let the user
     *               go back with the back button if it wanted to
     */
    public void generateCreateCoursePage(String rideID) {
        // We used the position of the button that was pressed to stored the ride ID in shared Preferences to retreive it later
        SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_RIDE, 0).edit();
        editor.putString(BaseActivity.PREFS_RIDEID, rideID);
        editor.apply();
        Intent intent = new Intent(this, CreateNewCourse.class);
        startActivity(intent);
    }

    /**
     * Load the page with all courses link to your user
     */
    public void generateAllCoursesPage() {
        Intent intent = new Intent(this, DisplayAllCourses.class);
        startActivity(intent);
    }

    /**
     * Method to logout
     * an AlertDialog is created to ask you if you realy want to logout
     * if yes the shared preference is remove
     * if not the alertdialog is just close
     */
    public void logout() {
        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogCustom).create();
        alertDialog.setTitle("Disconnect");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Do you really want to disconnect from your account?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Disconnect", (dialog, which) -> {
            SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_LOGGED, 0).edit();
            editor.putInt(BaseActivity.PREFS_USERID, -1);
            editor.apply();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
            finish();
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", (dialog, which) -> alertDialog.dismiss());
        alertDialog.show();
    }

}