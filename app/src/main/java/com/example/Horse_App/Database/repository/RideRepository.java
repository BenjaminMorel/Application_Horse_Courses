package com.example.Horse_App.Database.repository;

import androidx.lifecycle.LiveData;

import com.example.Horse_App.Database.Entity.RideEntity;
import com.example.Horse_App.Database.firebase.RideListLiveData;
import com.example.Horse_App.Database.firebase.RideLiveData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RideRepository {

    private static RideRepository instance;

    private RideRepository() {
    }

    public static RideRepository getInstance() {
        if (instance == null) {
            synchronized (RideRepository.class) {
                if (instance == null) {
                    instance = new RideRepository();
                }
            }
        }
        return instance;
    }

    public RideListLiveData getAllRides() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("rides");
        return new RideListLiveData(reference);
    }

    public LiveData<RideEntity> getRide(final String id) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("rides")
                .child(id);
        return new RideLiveData(reference);
    }
}
