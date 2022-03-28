package com.example.Horse_App.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Horse_App.ArrayAdapter.CourseAdapter;
import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.Course;
import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.Database.repository.CourseRepository;
import com.example.Horse_App.Database.repository.RideRepository;
import com.example.Horse_App.R;

import java.util.List;

public class DisplayAllCourses extends AppCompatActivity {

    private TextView noRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_historique);
        generatePage();
    }

    /**
     * Method to load all different part of the page
     * It will create a repository object for the ride and the course
     * then it will ask for all courses with the right user ID and all rides
     * If they are no courses a message will be display on the page to advert you
     * than you currently have made no reservation
     * If you already have made reservation we create a courseAdapter to use on the
     * recycler view
     */
    private void generatePage() {
        CourseRepository courseRepository = ((BaseApp) getApplication()).getCourseRepository();
        RideRepository rideRepository = (((BaseApp) getApplication()).getRideRepository());

        SharedPreferences preferences = getSharedPreferences(BaseActivity.PREFS_LOGGED, 0);
        int userID = preferences.getInt(BaseActivity.PREFS_USERID, 1);

        System.out.println(userID + " id du user co");

        RecyclerView recyclerView = findViewById(R.id.allCourses);

        List<Course> courses = courseRepository.getCoursesByUser(getApplication(), userID);
        List<Ride> rides = rideRepository.getRides(getApplication());
        courses =  courseRepository.getCoursesByUser(getApplication(), userID);

        if(courses.isEmpty()){
            noRegistration = findViewById(R.id.noReservation);
            noRegistration.setText("You currently have no registration");
        }
        rides = rideRepository.getRides(getApplication());
        CourseAdapter courseAdapter = new CourseAdapter(courses,rides);
        courseAdapter.setDisplayAllCourses(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    /**
     *
     * @param idCourse ID of the course you want to delete
     * If the button delete is pressed this method is called
     * It will create an AlertDialog to ask you if your realy want
     * to delete this reservation if yes it will then ask the real method on the repository to
     * delete it from de Database
     */
    public void deleteCourse(int idCourse) {

        AlertDialog alertDialog = new AlertDialog.Builder(this,R.style.AlertDialogCustom).create();
        alertDialog.setTitle(getString(R.string.deleteCourse));
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Do you really want to delete this course ?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_delete), (dialog, which) -> {
            CourseRepository.getInstance().deleteByID(this.getApplication(), idCourse);
            Intent intent = new Intent(this, DisplayAllCourses.class);
            startActivity(intent);
            Toast.makeText(DisplayAllCourses.this, "Course delete with success", Toast.LENGTH_SHORT).show();
            finish();
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.action_cancel), (dialog, which) -> alertDialog.dismiss());
        alertDialog.show();
    }
}