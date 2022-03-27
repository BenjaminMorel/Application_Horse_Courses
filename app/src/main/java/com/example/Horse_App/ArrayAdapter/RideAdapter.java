package com.example.Horse_App.ArrayAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.R;
import com.example.Horse_App.activities.MainPage;

import java.util.ArrayList;
import java.util.List;

public class RideAdapter extends RecyclerView.Adapter<RideAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textRide_Description;
        public TextView ride_specification;
        public TextView ride_location;
        public Button buttonSelectCourse;
        public List<Drawable> allPictures = new ArrayList<>();
        public View row_view;

        public ViewHolder(View itemView) {

            super(itemView);
            textRide_Description = itemView.findViewById(R.id.descriptionShowCourse);
            buttonSelectCourse = itemView.findViewById(R.id.buttonSelectCourse);
            ride_specification = itemView.findViewById(R.id.course_specification);
            ride_location = itemView.findViewById(R.id.ride_location);
            row_view = itemView.findViewById(R.id.container_ride_info);

            Drawable martigny = itemView.getContext().getDrawable(R.drawable.paysage1);
            Drawable liddes = itemView.getContext().getDrawable(R.drawable.paysage2);
            Drawable saillon = itemView.getContext().getDrawable(R.drawable.paysage3);
            Drawable sierre = itemView.getContext().getDrawable(R.drawable.paysage4);

            allPictures.add(martigny);
            allPictures.add(liddes);
            allPictures.add(saillon);
            allPictures.add(sierre);
        }
    }

    private MainPage mainPage;
    private List<Ride> rides;

    public RideAdapter(List<Ride> rides){this.rides = rides; }
    @Override
    public RideAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.fragment_show_ride, parent, false);
        // Return a new holder instance
        RideAdapter.ViewHolder viewHolder = new RideAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RideAdapter.ViewHolder holder, int position) {
        Ride ride = rides.get(position);

        holder.row_view.setBackground(holder.allPictures.get(position));
      //  holder.row_view.setBackground(picture);

        holder.ride_location.setText(ride.location);
        holder.textRide_Description.setText(ride.description);
        holder.ride_specification.setText("Difficulty :    " + ride.difficulty + "/5" + "     " + "Length :    " + ride.length + "\nDuration :    " + ride.duration);
        holder.buttonSelectCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPage.generateCreateCoursePage(ride.rideID);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rides.size();
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }


}
