package com.example.Horse_App.ArrayAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.Course;
import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.Database.repository.CourseRepository;
import com.example.Horse_App.Database.repository.RideRepository;
import com.example.Horse_App.R;
import com.example.Horse_App.activities.DisplayAllCourses;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;

import org.w3c.dom.Text;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView course_date;
        public TextView course_location;
        public Button cancelButton;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            course_location = itemView.findViewById(R.id.course_location);
            cancelButton = itemView.findViewById(R.id.cancelCourseButton);
            course_date = itemView.findViewById(R.id.course_date);
        }
    }

    private final List<Course> courses;
    private final List<Ride> rides;

    public DisplayAllCourses displayAllCourses;

    public RideRepository rideRepository;

    // Pass in the contact array into the constructor
    public CourseAdapter(List<Course> courses, List<Ride> rides) {
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

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Course course = courses.get(position);
        Ride ride = new Ride();
        for (Ride r : rides) {
            if (course.rideID == r.rideID) {
                ride = r;
            }
        }
        holder.course_location.setText(ride.location);
        holder.course_date.setText(course.date);
        holder.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAllCourses.deleteCourse(course.courseID, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setDisplayAllCourses(DisplayAllCourses displayAllCourses) {
        this.displayAllCourses = displayAllCourses;
    }
}
