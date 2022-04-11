package com.example.Horse_App.Database.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.Horse_App.Database.Entity.RideEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RideListLiveData extends LiveData<List<RideEntity>> {

    private static final String TAG = "RideLiveData";
    private final DatabaseReference reference;
    private final MyValueEventListener listener = new MyValueEventListener();

    public RideListLiveData(DatabaseReference reference) {
        this.reference = reference;
    }

    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }

    class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(toRideList(dataSnapshot));
       }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<RideEntity> toRideList(DataSnapshot snapshot) {
        List<RideEntity> rideEntities = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            RideEntity entity = childSnapshot.getValue(RideEntity.class);
            entity.setRideID(childSnapshot.getKey());
            rideEntities.add(entity);
        }
        return rideEntities;
    }
}
