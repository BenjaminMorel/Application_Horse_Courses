package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.ArrayAdapter.CustomeCourseItem;
import com.example.Horse_App.Database.Entity.Course;
import com.example.Horse_App.Database.repository.CourseRepository;
import com.example.Horse_App.R;

import java.util.List;

public class DisplayAllCourses extends AppCompatActivity {


    private CourseRepository courseRepository;
    private List<Course> courses;
    private ListView listView;
    private int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_historique);

        generatePage();
    }

    private void generatePage(){
        courseRepository = ((BaseApp) getApplication()).getCourseRepository();

        SharedPreferences preferences = getSharedPreferences(BaseActivity.PREFS_USERID, 0);
        userID = preferences.getInt(BaseActivity.PREFS_USERID,1);

        listView = findViewById(R.id.allCourses);

        courseRepository.getCoursesByUser(getApplication(),userID).observe(this,  allCourse ->{
            CustomeCourseItem customeCourseItem = new CustomeCourseItem(this, allCourse);
            listView.setAdapter(customeCourseItem);
        } );
    }


}