package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpage.DBObject.Course;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class UserMainPage extends AppCompatActivity {


    private ListView listView;

    private List allCourses = new ArrayList<Course>();

    private CustomCourseItem customCourseItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        Toast toast = Toast.makeText(this,"Login successful", Toast.LENGTH_LONG );
        toast.show();

        generateList();
        listView = (ListView) findViewById(R.id.list_Course);

        customCourseItem = new CustomCourseItem(this, allCourses);
        customCourseItem.setUserMainPage(this);
        listView.setAdapter(customCourseItem);
    }

    public void editProfile(View view){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    public void purchaseCourse(View view, int position){
        setContentView(R.layout.fragment_register__course_);
        setValueOnInfoPage(position);
    }

    public void backToMainPage(View view){

        setContentView(R.layout.activity_user_main_page);
        listView = (ListView) findViewById(R.id.list_Course);
        CustomCourseItem customCourseItem = new CustomCourseItem(this, allCourses);
        listView.setAdapter(customCourseItem);
        customCourseItem.setUserMainPage(this);
    }

    public void generateList(){
        Course course1 = new Course("Martigny",4,2.0,5.6,60.00,"Super balade au bord de la Dranse");
        Course course2 = new Course("Sion", 1, 1.0, 2.4,25.00,"Balade jusqu'au chateau et vue sur la ville");
        Course course3 = new Course("Sierre", 1, 1.5, 3.2,32.50,"Super promenade au bord du Rhone");
        allCourses.add(course1);
        allCourses.add(course2);
        allCourses.add(course3);
    }

    public void setValueOnInfoPage(int position){
        TextView description = findViewById(R.id.purchaseCourse_title);
        TextView length = findViewById(R.id.purchaseCourse_length);
        TextView duration = findViewById(R.id.purchaseCourse_duration);
        TextView difficulty = findViewById(R.id.purchaseCourse_difficulty);
        TextView price = findViewById(R.id.purchaseCourse_price);

        description.setText(customCourseItem.getAllCourses().get(position).getDescription());
        length.setText("Lenght : " + String.valueOf(customCourseItem.getAllCourses().get(position).getLength()) + " KM");
        duration.setText("Duration : " +String.valueOf(customCourseItem.getAllCourses().get(position).getDuration()) + " Heure");
        difficulty.setText("Difficulty : " + String.valueOf(customCourseItem.getAllCourses().get(position).getDifficulty()) + "/5");
        price.setText("Price : " + String.valueOf(customCourseItem.getAllCourses().get(position).getPrice()) + " CHF");

    }

    public void loadMap(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}