package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserMainPage extends AppCompatActivity {


    private ListView listView;

    private List dates = new ArrayList<String>();
    private List prices = new ArrayList<String>();

    private CustomCourseItem customCourseItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        Toast toast = Toast.makeText(this,"Login successful", Toast.LENGTH_LONG );
        toast.show();

        generateList();
        listView = (ListView) findViewById(R.id.list_Course);

        customCourseItem = new CustomCourseItem(this, dates,prices);
        customCourseItem.setUserMainPage(this);
        listView.setAdapter(customCourseItem);
    }

    public void editProfile(View view){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    public void purchaseCourse(View view, int position){
        setContentView(R.layout.fragment_register__course_);
        TextView textView = findViewById(R.id.purchaseCourse_title);
        textView.setText("Purschase course number : " + position);
    }

    public void backToMainPage(View view){

        setContentView(R.layout.activity_user_main_page);
        listView = (ListView) findViewById(R.id.list_Course);
        CustomCourseItem customCourseItem = new CustomCourseItem(this, dates,prices);
        listView.setAdapter(customCourseItem);
        customCourseItem.setUserMainPage(this);
    }

    public void generateList(){
        dates = new ArrayList<String>();
        prices = new ArrayList<String>();
        for(int i = 0 ; i < 20; i++){
            dates.add(i+"0/"+i + i+ "/2022");
            int price = i *10;
            prices.add(price + " CHF");
        }
    }

}