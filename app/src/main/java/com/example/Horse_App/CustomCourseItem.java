package com.example.Horse_App;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.Horse_App.Database.Entity.Course;
import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.activities.MainPage;

import java.util.List;

public class CustomCourseItem extends ArrayAdapter {

    private Activity context ;
    private List<Ride> rides;
    private MainPage mainPage;

    public CustomCourseItem(Activity context,  List<Ride> rides){
        super(context,R.layout.fragment_show__ride,rides);
        this.context = context;
        this.rides = rides;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null) {
            row = inflater.inflate(R.layout.fragment_show__ride, null, true);
        }
        TextView textViewDescription = (TextView)  row.findViewById(R.id.descriptionShowCourse);
        textViewDescription.setText(rides.get(position).description);

        Button buttonSelectCourse = row.findViewById(R.id.buttonSelectCourse);
        buttonSelectCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPage.generateCreateCoursePage();
            }
        });


        return row;
    }


    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }
}
