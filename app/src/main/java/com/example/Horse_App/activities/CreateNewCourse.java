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

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.CourseEntity;
import com.example.Horse_App.Database.Entity.RideEntity;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.repository.CourseRepository;
import com.example.Horse_App.Database.repository.RideRepository;
import com.example.Horse_App.Fragments.MapsFragment;
import com.example.Horse_App.R;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateNewCourse extends AppCompatActivity {

    private static final String TAG = "CreateNewCourseActivity";
    private String rideID;
    private CourseRepository courseRepository;
    private Date selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_course);
        courseRepository = ((BaseApp) getApplication()).getCourseRepository();
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
        rideID = preferences.getString(BaseActivity.PREFS_RIDEID, "");
        rideRepository.getRide(rideID).observe(this, ride -> {
                FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
                SupportMapFragment supportMapFragment = new MapsFragment(ride.getPositions());
                mTransaction.add(R.id.mapFragment, supportMapFragment);
                mTransaction.commit();
                setTextViewValue(ride);
        });

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

        // Set min date
        calendarView.setMinDate(now + 1000L * 60 * 60 * 24);
        Calendar calendar = Calendar.getInstance();
        now += (60 * 1000L * 60 * 60 * 24);

        // Set max date
        calendarView.setMaxDate(now);

        // Set default value for selectedDate
        selectedDate = calendar.getTime();

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

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String courseDate = sdf.format(selectedDate);

        CourseEntity newCourse = new CourseEntity(rideID, userID, courseDate);
        courseRepository.insert(newCourse,new OnAsyncEventListener() {
                    //            @Override
            public void onSuccess() {
                Log.d("1", "createUserWithEmail: success");
            }

            @Override
            public void onFailure(Exception e) {
                Log.d("1", "createUserWithEmail: failure", e);
            }
        });
        finish();
    }

    /**
     * Method that will wait for the fragment map to be loaded
     * And after that it call the Callback method on the fragment class
     * to position the cart at the rigth place and set the view correctly
     */

    /**
     * Method to set all TextView with the right value depending on the ride you choose
     */
    private void setTextViewValue(RideEntity ride) {

        TextView startHour = findViewById(R.id.StartHour);
        TextView finishHour = findViewById(R.id.FinishHour);
        TextView coursePrice = findViewById(R.id.course_price);
        String[] hours = ride.getTime().split("/");

        startHour.setText("Starts at " + hours[0]);
        finishHour.setText("Ends at "+ hours[1]);
        coursePrice.setText(ride.getPrice() + " CHF");
    }
}