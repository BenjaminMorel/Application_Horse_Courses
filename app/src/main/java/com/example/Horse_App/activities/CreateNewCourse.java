package com.example.Horse_App.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.Course;
import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.async.Course.CreateCourse;
import com.example.Horse_App.Database.repository.CourseRepository;
import com.example.Horse_App.Database.repository.RideRepository;
import com.example.Horse_App.Fragments.MapsFragment;
import com.example.Horse_App.R;
import com.google.android.gms.maps.SupportMapFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateNewCourse extends AppCompatActivity {

    private static final String TAG = "CreateNewCourseActivity";
    private int rideID;
    private int userID;
    private Ride ride;

    private Button confirmCreation;
    private CalendarView calendarView;
    private MapsFragment mapsFragment;

    private RideRepository rideRepository;
    private CourseRepository courseRepository;

    private TextView startHour;
    private TextView finishHour;
    private TextView titleLocation;
    private TextView coursePrice;

    private Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_course);

        createPage();

    }

    private void createPage(){

        rideRepository = ((BaseApp) getApplication()).getRideRepository();
        SharedPreferences preferences = getSharedPreferences(BaseActivity.PREFS_RIDEID, 0);

        rideID = preferences.getInt(BaseActivity.PREFS_USERID,1);

        // after using the shared pref we get rid of it to be sure that the variable is empty for the next use
        SharedPreferences.Editor editor = getSharedPreferences(BaseActivity.PREFS_RIDEID, 0).edit();
        editor.remove(BaseActivity.PREFS_RIDEID);
        editor.apply();

        ride = rideRepository.getRide(rideID,getApplication());

        setTextViewValue();

        initializeMapsFragment();
        confirmCreation = findViewById(R.id.ButtonConfirmNewCourse);

        confirmCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewCourse();
            }
        });

        // Disable past days
        calendarView = findViewById(R.id.calendarNewCourse);
        long now = calendarView.getDate();
        calendarView.setMinDate(now);
        Calendar calendar = Calendar.getInstance();
        now += (60*1000L * 60 * 60 * 24);
        calendarView.setMaxDate(now);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                selectedDate = new GregorianCalendar(year,month,day).getTime();
            }
        });
    }

    private void createNewCourse(){

        SharedPreferences userPreferences = getSharedPreferences(BaseActivity.PREFS_USERID, 0);

        userID = userPreferences.getInt(BaseActivity.PREFS_USERID,1);

//        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String courseDate = sdf.format(selectedDate);
     //   String courseDate = "22/03/2022";

        Course newCourse = new Course(ride.rideID, userID , courseDate);

        new CreateCourse(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "create new Course: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "Create new Course: failure", e);
            }
        }).execute(newCourse);

        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);

    }

        private void initializeMapsFragment() {
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
        mapsFragment = new MapsFragment(ride.getPositions());
        SupportMapFragment supportMapFragment = mapsFragment;
        mTransaction.add(R.id.mapFragment, supportMapFragment);
        mTransaction.commit();
    }

    @SuppressLint("SetTextI18n")
    private void setTextViewValue(){

        startHour = findViewById(R.id.StartHour);
        finishHour = findViewById(R.id.FinishHour);
        coursePrice = findViewById(R.id.course_price);

        String [] hours = ride.time.split("/");

        startHour.setText("Starts at: " + hours[0]);
        finishHour.setText("Ends at: " + hours[1]);
        coursePrice.setText(String.valueOf(ride.price) + " CHF");
    }
}