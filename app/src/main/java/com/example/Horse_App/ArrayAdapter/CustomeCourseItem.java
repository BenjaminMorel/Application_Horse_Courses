package com.example.Horse_App.ArrayAdapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.Horse_App.Database.Entity.Course;
import com.example.Horse_App.Database.repository.CourseRepository;
import com.example.Horse_App.R;
import com.example.Horse_App.activities.DisplayAllCourses;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomeCourseItem extends ArrayAdapter {

    private Activity context;
    private List<Course> courses;

    private TextView courseInfo;
    private Button cancelCourse;

    private Date date;
    private int courseID;

    private DisplayAllCourses displayAllCourses;

    public CustomeCourseItem(Activity context, List<Course> courses) {
        super(context, R.layout.fragment_course_display, courses);
        this.context = context;
        this.courses = courses;
    }

    //TODO regler probleme d'id
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            row = inflater.inflate(R.layout.fragment_course_display, null, true);
        }
        courseID = courses.get(position).courseID;
        System.out.println(courses.get(position).getCourseID());

        courseInfo = row.findViewById(R.id.infoCourse);
        cancelCourse = row.findViewById(R.id.cancelCourseButton);
        try {
            Date courseDate = new SimpleDateFormat("dd/MM/yyyy").parse(courses.get(position).getDate());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, +7);
            Date actualDate = cal.getTime();
            if (courseDate.compareTo(actualDate) < 0) {
                cancelCourse.setVisibility(View.INVISIBLE);
            }

        } catch (Exception e) {
            Log.d("Get the courses date", "parse the date of the course: Failed");
        }

        cancelCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    displayAllCourses.deleteCourse(courseID);
            }
        });

        courseInfo.setText("You choose ride number : " + courses.get(position).rideID + " will take place on " + courses.get(position).getDate());
        return row;
    }

    public void setDisplayAllCourses(DisplayAllCourses displayAllCourses) {
        this.displayAllCourses = displayAllCourses;
    }
}
