package com.example.Horse_App.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

    private CourseRepository courseRepository;
    private RideRepository rideRepository;
    private List<Course> courses;
    private List<Ride> rides;
    private RecyclerView recyclerView;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_historique);

        generatePage();
    }

    private void generatePage() {
        courseRepository = ((BaseApp) getApplication()).getCourseRepository();
        rideRepository = (((BaseApp) getApplication()).getRideRepository());

        SharedPreferences preferences = getSharedPreferences(BaseActivity.PREFS_USERID, 0);
        userID = preferences.getInt(BaseActivity.PREFS_USERID, 1);

        recyclerView = findViewById(R.id.allCourses);

        courses = courseRepository.getCoursesByUser(getApplication(), userID);
        rides = rideRepository.getRides(getApplication());
        CourseAdapter courseAdapter = new CourseAdapter(courses, rides);
        courseAdapter.setDisplayAllCourses(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public void deleteCourse(int idCourse, int position) {

        AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogCustom).create();
        alertDialog.setTitle("Delete Course");
        alertDialog.setCancelable(false);
        alertDialog.setMessage("Do you really want to delete this course ?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Delete", (dialog, which) -> {
            CourseRepository.getInstance().deleteByID(this.getApplication(), idCourse);
            Intent intent = new Intent(this, DisplayAllCourses.class);
            startActivity(intent);
            Toast.makeText(DisplayAllCourses.this, "Course delete with success", Toast.LENGTH_SHORT).show();
            finish();
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", (dialog, which) -> alertDialog.dismiss());
        alertDialog.show();
    }
}