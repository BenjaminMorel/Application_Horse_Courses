package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpage.DBObject.Course;

import java.util.ArrayList;
import java.util.List;

public class UserMainPage extends AppCompatActivity {


    private ListView listView;

    private List definitions = new ArrayList<String>();
    private List prices = new ArrayList<String>();

    private Button selectCourse;
    private String definition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        Toast toast = Toast.makeText(this,"Login successful", Toast.LENGTH_LONG );
        toast.show();

        generateList();
        listView = (ListView) findViewById(R.id.list_Course);

        CustomCourseItem customCourseItem = new CustomCourseItem(this,definitions,prices);
        listView.setAdapter(customCourseItem);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                definition = definitions.get(position).toString();
            }
        });
        selectCourse = findViewById(R.id.buttonSelectCourse);


    }

    public void editProfile(View view){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    public void purchaseCourse(View view){
        setContentView(R.layout.fragment_register__course_);
        TextView descritpion = findViewById(R.id.testCoursInfo);
        descritpion.setText(definition);
    }

    public void backToMainPage(View view){

        setContentView(R.layout.activity_user_main_page);
        listView = (ListView) findViewById(R.id.list_Course);

        CustomCourseItem customCourseItem = new CustomCourseItem(this,definitions,prices);
        listView.setAdapter(customCourseItem);


    }

    public void generateList(){

          definitions = new ArrayList<String>();
          prices = new ArrayList<String>();

        for(int i = 0 ; i < 20; i++){
            definitions.add("Hello world " + i);
            int price = i *10;
            prices.add(price + " CHF");
        }
    }

}