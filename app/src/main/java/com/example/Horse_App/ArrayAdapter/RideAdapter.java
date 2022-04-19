package com.example.Horse_App.ArrayAdapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.Horse_App.Database.Entity.RideEntity;
import com.example.Horse_App.R;
import com.example.Horse_App.activities.MainPage;
import java.util.ArrayList;
import java.util.List;

public class RideAdapter extends RecyclerView.Adapter<RideAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textRide_Description, ride_specification, ride_location;
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
    private List<RideEntity> rides;

    public RideAdapter(List<RideEntity> rides) {
        this.rides = rides;
    }

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
        RideEntity ride = rides.get(position);

        holder.row_view.setBackground(holder.allPictures.get(position));
        holder.ride_location.setText(ride.getLocation());
        holder.textRide_Description.setText(ride.getDescription());
        holder.ride_specification.setText("Difficulty :    " + ride.getDifficulty() + "/5" + "     " + "Length :    " + ride.getLength() + " km" + "\nDuration :    " + ride.getDuration() + " h.");
        holder.buttonSelectCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPage.generateCreateCoursePage(ride.getRideID());
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
