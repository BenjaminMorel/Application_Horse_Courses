package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.loginpage.DBObject.Course;

import java.util.ArrayList;
import java.util.List;

public class UserMainPage extends AppCompatActivity {

    private List course = new ArrayList<Course>();
    private List course2 = new ArrayList<NewCourseIcone>();
    private List str = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        generateCourse();
        ListView listView = findViewById(R.id.course_list);
        ArrayAdapter adapter = new ArrayAdapter<NewCourseIcone>(this,android.R.layout.simple_list_item_1,str);
        listView.setAdapter(adapter);

    }

    public void editProfile(View view){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    public void purchaseCourse(View view){
        Intent intent = new Intent(this, PurchaseCourse.class);
        startActivity(intent);
    }

    public void generateCourse(){
        for(int i = 0; i< 10; i++){
            Course cours = new Course(i,20, i+" Nice ride on Benou");
            course.add(cours);
            str.add(cours.getDescription());
        }


    }

    public void generateCourse2(){
        for(int i = 0; i< 20; i++){
          NewCourseIcone var = new NewCourseIcone() ;
            course2.add(var);
        }
    }


}