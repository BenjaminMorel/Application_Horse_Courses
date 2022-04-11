package com.example.Horse_App.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.*;
import com.example.Horse_App.Database.Entity.CourseEntity;
import com.example.Horse_App.Database.Entity.RideEntity;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.async.Course.CreateCourse;
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
    private String rideID;
    private LiveData<RideEntity> ride;
    private Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_course);
        createPage();
    }


    /**
     * Method use to create all information on the page
     * We first get back the Ride ID to display the write Ride
     * Then we set all TextView with the rigth information
     * The maps si load with the location point stored in the data based
     * And final the calendar is set on the date of the next day
     * and date are blocked for the past and after 3 month in the future
     */
    private void createPage() {
        RideRepository rideRepository = ((BaseApp) getApplication()).getRideRepository();
        SharedPreferences preferences = getSharedPreferences(BaseActivity.PREFS_RIDE, 0);
        rideID = String.valueOf(preferences.getInt(BaseActivity.PREFS_RIDEID, 1));
        ride = rideRepository.getRide(rideID);

        setTextViewValue();

        initializeMapsFragment();
        Button confirmCreation = findViewById(R.id.ButtonConfirmNewCourse);

        confirmCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewCourse();
            }
        });

        // Disable past days
        CalendarView calendarView = findViewById(R.id.calendarNewCourse);
        long now = calendarView.getDate();
        calendarView.setDate(now + 1000L * 60 * 60 * 24);
        calendarView.setMinDate(now);
        Calendar calendar = Calendar.getInstance();
        now += (60 * 1000L * 60 * 60 * 24);
        calendarView.setMaxDate(now);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                selectedDate = new GregorianCalendar(year, month, day).getTime();
            }
        });
    }

    /**
     * Method to handle the reservation of the new course
     * We get back the date that was selected by the user
     * and we used the ride ID and User ID as foreign key
     * for the newly created course
     * If the creation is a success we display a toast for
     * the user
     */
    private void createNewCourse() {

        SharedPreferences userPreferences = getSharedPreferences(BaseActivity.PREFS_LOGGED, 0);

        String userID = String.valueOf(userPreferences.getInt(BaseActivity.PREFS_USERID, 1));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String courseDate = sdf.format(selectedDate);

        CourseEntity newCourse = new CourseEntity(rideID, userID, courseDate);

        new CreateCourse(getApplication(), new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(CreateNewCourse.this, R.string.toast_reservationSuccessful, Toast.LENGTH_SHORT).show();
                Log.d(TAG, getString(R.string.log_createCourseSuccess));
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, getString(R.string.log_createCourseFailure) + rideID + " / " + userID, e);
            }

        }).execute(newCourse);

        finish();
    }

    /**
     * Method that will wait for the fragment map to be loaded
     * And after that it call the Callback method on the fragment class
     * to position the cart at the rigth place and set the view correctly
     */
    private void initializeMapsFragment() {
        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
//        SupportMapFragment supportMapFragment = new MapsFragment(ride.getPositions());
//        mTransaction.add(R.id.mapFragment, supportMapFragment);
        mTransaction.commit();
    }


    /**
     * Method to set all TextView with the right value depending on the ride you choose
     */
    private void setTextViewValue() {

        TextView startHour = findViewById(R.id.StartHour);
        TextView finishHour = findViewById(R.id.FinishHour);
        TextView coursePrice = findViewById(R.id.course_price);

//        String[] hours = ride.time.split("/");

//        startHour.setText("Starts at " + hours[0]);
//        finishHour.setText("Ends at "+ hours[1]);
//        coursePrice.setText(String.valueOf(ride.price) + " CHF");
    }
}