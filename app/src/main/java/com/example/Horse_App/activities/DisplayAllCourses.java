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

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_historique);

        generatePage();
    }

    private void generatePage() {
        CourseRepository courseRepository = ((BaseApp) getApplication()).getCourseRepository();

        SharedPreferences preferences = getSharedPreferences(BaseActivity.PREFS_USERID, 0);
        int userID = preferences.getInt(BaseActivity.PREFS_USERID, 1);

        listView = findViewById(R.id.allCourses);

        courses =  courseRepository.getCoursesByUser(getApplication(), userID);
        CustomeCourseItem customeCourseItem = new CustomeCourseItem(this, courses);
        listView.setAdapter(customeCourseItem);
        customeCourseItem.setDisplayAllCourses(this);

    }

    public void deleteCourse(int idCourse) {
        System.out.println("Trying to delete " + idCourse);
        CourseRepository.getInstance().deleteByID(this.getApplication(), idCourse);
    }
}