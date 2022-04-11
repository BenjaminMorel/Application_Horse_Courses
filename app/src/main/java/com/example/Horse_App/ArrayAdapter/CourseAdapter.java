package com.example.Horse_App.ArrayAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Horse_App.Database.Entity.CourseEntity;
import com.example.Horse_App.Database.Entity.RideEntity;
import com.example.Horse_App.Database.repository.RideRepository;
import com.example.Horse_App.R;
import com.example.Horse_App.activities.DisplayAllCourses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // List of variable that will be used for each row
        public TextView course_date;
        public TextView course_location;
        public Button cancelButton;

        // the constructor will find the right variable for the textView and the button
        public ViewHolder(View itemView) {
            super(itemView);

            course_location = itemView.findViewById(R.id.course_location);
            cancelButton = itemView.findViewById(R.id.cancelCourseButton);
            course_date = itemView.findViewById(R.id.course_date);
        }
    }

    // List of courses and rides that will be used to display informations

    private final List<CourseEntity> courses;
    private final List<RideEntity> rides;

    // reference to the activity to be able to access the delete methode
    public DisplayAllCourses displayAllCourses;

    public RideRepository rideRepository;

    // Pass in the contact array into the constructor
    public CourseAdapter(List<CourseEntity> courses, List<RideEntity> rides) {
        this.courses = courses;
        this.rides = rides;
    }

    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.fragment_course_display, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }


    /**
     *
     * For each row we will set the text accordingly with the database and we calcutate if
     * the cancel button should by display or not ( if the course is in less than 7 day it will
     * be disable), if it's enable we create an OnClickListener link to the deleteCourse on the display page
     *
     */
    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        CourseEntity course = courses.get(position);
        RideEntity ride = new RideEntity();
        for (RideEntity r : rides) {
            if (course.getRideID() == r.getRideID()) {
                ride = r;
            }
        }
        holder.course_location.setText(ride.getLocation());
        holder.course_date.setText(course.getDate());

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            Date courseDate =new SimpleDateFormat("dd/MM/yyyy").parse(course.getDate());
            if(courseDate.compareTo(calendar.getTime()) < 1){

                holder.cancelButton.setVisibility(View.INVISIBLE);
            }
        } catch (ParseException e) {

            e.printStackTrace();
        }

        holder.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAllCourses.deleteCourse(course.getCourseID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    /**
     *
     * Setter for the reference of the activity that hold the recyclerView
     */
    public void setDisplayAllCourses(DisplayAllCourses displayAllCourses) {
        this.displayAllCourses = displayAllCourses;
    }
}
