package com.example.Horse_App.ArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.R;
import com.example.Horse_App.activities.MainPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CustomRideItem extends ArrayAdapter {

    private Activity context;
    private List<Ride> rides;
    private MainPage mainPage;
    private int rideID;
    private List<Drawable> allPictures = new ArrayList<>();


    public CustomRideItem(Activity context, List<Ride> rides) {
        super(context, R.layout.fragment_show_ride, rides);
        this.context = context;
        this.rides = rides;
        setAllPictures();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            row = inflater.inflate(R.layout.fragment_show_ride, null, true);
        }

        row.setBackground(allPictures.get(position));
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


    public void setAllPictures(){
        Drawable martigny = context.getDrawable(R.drawable.martigny);
        Drawable saillon = context.getDrawable(R.drawable.saillon);
        Drawable liddes = context.getDrawable(R.drawable.liddes);
        Drawable sierre = context.getDrawable(R.drawable.paysage);

        allPictures.add(martigny);

        allPictures.add(liddes);
        allPictures.add(saillon);
        allPictures.add(sierre);
    }
    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }
}
