package com.example.Horse_App.ArrayAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.R;
import com.example.Horse_App.activities.MainPage;

import java.util.List;

public class CustomRideItem extends ArrayAdapter {

    private Activity context;
    private List<Ride> rides;
    private MainPage mainPage;
    private int rideID;

    public CustomRideItem(Activity context, List<Ride> rides) {
        super(context, R.layout.fragment_show_ride, rides);
        this.context = context;
        this.rides = rides;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            row = inflater.inflate(R.layout.fragment_show_ride, null, true);
        }

        rideID = rides.get(position).rideID;
        TextView textRideLocation = (TextView) row.findViewById(R.id.descriptionShowCourse);
        textRideLocation.setText(rides.get(position).location);

        Button buttonSelectCourse = row.findViewById(R.id.buttonSelectCourse);
        buttonSelectCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mainPage.generateCreateCoursePage(rideID);
            }
        });


        return row;
    }


    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }
}
