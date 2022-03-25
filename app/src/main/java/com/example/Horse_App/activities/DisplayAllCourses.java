package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.Horse_App.ArrayAdapter.CourseAdapter;
import com.example.Horse_App.BaseApp;
import com.example.Horse_App.ArrayAdapter.CustomeCourseItem;
import com.example.Horse_App.Database.Entity.Course;
import com.example.Horse_App.Database.repository.CourseRepository;
import com.example.Horse_App.R;

import java.util.List;

public class DisplayAllCourses extends AppCompatActivity {

    private CourseRepository courseRepository;
    private List<Course> courses;
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

        SharedPreferences preferences = getSharedPreferences(BaseActivity.PREFS_USERID, 0);
        userID = preferences.getInt(BaseActivity.PREFS_USERID, 1);

        recyclerView = findViewById(R.id.allCourses);

        courses =  courseRepository.getCoursesByUser(getApplication(), userID);

        CourseAdapter courseAdapter = new CourseAdapter(courses);
        courseAdapter.setDisplayAllCourses(this);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public void deleteCourse(int idCourse,int position) {
        CourseRepository.getInstance().deleteByID(this.getApplication(), idCourse);
        Intent intent = new Intent(this, DisplayAllCourses.class);
        startActivity(intent);
        finish();
    }
}