package com.example.Horse_App.ArrayAdapter;

import android.annotation.SuppressLint;
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
import java.util.Date;
import java.util.List;

public class CustomeCourseItem extends ArrayAdapter {

    private final Activity context;
    private final List<Course> courses;

    private Date date;
    private int courseID;

    private DisplayAllCourses displayAllCourses;

    public CustomeCourseItem(Activity context, List<Course> courses) {
        super(context, R.layout.fragment_course_display, courses);
        this.context = context;
        this.courses = courses;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            row = inflater.inflate(R.layout.fragment_course_display, null, true);
        }
        courseID = courses.get(position).courseID;
        TextView courseInfo = row.findViewById(R.id.infoCourse);
        Button cancelCourse = row.findViewById(R.id.cancelCourseButton);
        LocalDateTime now = LocalDateTime.now();
        try {
            @SuppressLint("SimpleDateFormat") Date courseDate = new SimpleDateFormat("dd/MM/yyyy").parse(courses.get(position).getDate());
            Date actualDate = new Date();
            System.out.println(courseDate.compareTo(actualDate));
            if (courseDate.compareTo(actualDate) < 0) {
                cancelCourse.setText("super fun");

                cancelCourse.setVisibility(View.INVISIBLE);
            } else {
                cancelCourse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteCourse();
                    }
                });
            }
            Log.d("Get the courses date", "parse the date of the course: Succes");
        } catch (Exception e) {
            Log.d("Get the courses date", "parse the date of the course: Failed");
        }

        courseInfo.setText("You choose ride number : " + courses.get(position).rideID + " will take place on " + courses.get(position).getDate());
        return row;
    }

    private void deleteCourse() {
        CourseRepository.getInstance().deleteByID(context.getApplication(), courseID);
    }
}
