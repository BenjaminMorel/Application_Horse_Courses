package com.example.Horse_App.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ListView;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.CustomCourseItem;
import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.Database.repository.RideRepository;
import com.example.Horse_App.R;
import com.example.Horse_App.ViewModel.RideListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {

    private RideRepository repository;
    private List<Ride> rides = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        startMainPage();
    }

    private void startMainPage(){

        repository = ((BaseApp) getApplication()).getRideRepository();
//        RideListViewModel.Factory factory = new RideListViewModel.Factory(getApplication());
//        RideListViewModel viewModel = new ViewModelProvider(this, factory).get(RideListViewModel.class);

//        RideListViewModel rideListViewModel = new RideListViewModel(getApplication(),repository);
//        rideListViewModel.getRides().observe(this, ridesEntities -> {
//            if(ridesEntities != null){
//                System.out.println(ridesEntities.size());
//                for(Ride ride : ridesEntities){
//                    rides.add(ride);
//                    System.out.println("SALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLUT");
//                }
//            }
//        });

        ListView listView = findViewById(R.id.ListRideToChoose);
        repository.getRides(getApplication()).observe(this, ridesEntities -> {
            if(ridesEntities != null){

                    CustomCourseItem customCourseItem = new CustomCourseItem(this, ridesEntities);
                    listView.setAdapter(customCourseItem);


            }
        });

    }
}