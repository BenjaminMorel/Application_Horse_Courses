package com.example.Horse_App.Database.repository;

import androidx.lifecycle.LiveData;
import com.example.Horse_App.Database.Entity.RideEntity;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.firebase.RideListLiveData;
import com.example.Horse_App.Database.firebase.RideLiveData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.Query;
import com.google.firestore.admin.v1.Index;

import java.util.List;

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

    public LiveData<List<RideEntity>> getAllRides(){
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

    public void insertRide(final RideEntity ride, final OnAsyncEventListener callback) {
        String id = FirebaseDatabase.getInstance().getReference("rides").push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("rides")
                .child(id)
                .setValue(ride.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }
}
