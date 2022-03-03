package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.loginpage.DBObject.Course;

import java.util.ArrayList;
import java.util.List;

public class UserMainPage extends FragmentActivity {


    private List definitions = new ArrayList<String>();
    private List prices = new ArrayList<String>();
    private List courseIcone = new ArrayList<NewCourseIcone>();
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        Toast toast = Toast.makeText(this,"Login successful", Toast.LENGTH_LONG );
        toast.show();

        generateList();
        ListView listView = (ListView) findViewById(R.id.list_Course);
        CustomCourseItem customCourseItem = new CustomCourseItem(this,definitions,prices);
        listView.setAdapter(customCourseItem);

    }

    public void editProfile(View view){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    public void purchaseCourse(View view){
        Intent intent = new Intent(this, PurchaseCourse.class);
        startActivity(intent);
    }

    public void generateList(){
        for(int i = 0 ; i < 20; i++){
            definitions.add("Hello world " + i);
            int price = i *10;
            prices.add(price + " CHF");
        }
    }



}