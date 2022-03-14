package com.example.Horse_App;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.Horse_App.Database.Entity.Course;
import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.OLD.UserMainPage;

import java.util.List;

public class CustomCourseItem extends ArrayAdapter {

    private Activity context ;
    private UserMainPage userMainPage;
    private List<Course> allCourses;
    private List<Ride> rides;

    public CustomCourseItem(Activity context, List<Course> allCourses, List<Ride> rides){
        super(context,R.layout.fragment_new_course_icone,allCourses);
        this.context = context;
        this.allCourses = allCourses;
        this.rides = rides;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null) {
            row = inflater.inflate(R.layout.fragment_new_course_icone, null, true);
        }
        TextView textViewDescription = (TextView)  row.findViewById(R.id.textNewCourse);
        Button buttonSelectCourse = (Button)   row.findViewById(R.id.buttonSelectCourse);

        int rideID = allCourses.get(position).getRideID();
        Ride ride = null;
        for(Ride r : rides){
            if(r.getRideID() == rideID){
                ride = r;
                break;
            }
        }
        textViewDescription.setText(ride.getLocation());
        buttonSelectCourse.setText(String.valueOf(allCourses.get(position).getPrice()) + " CHF");

        buttonSelectCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userMainPage.purchaseCourse(view,position);
            }
        });

        return row;
    }

    public void setUserMainPage(UserMainPage userMainPage){
        this.userMainPage = userMainPage;
    }

    public List<Course> getAllCourses() {
        return allCourses;
    }
}
