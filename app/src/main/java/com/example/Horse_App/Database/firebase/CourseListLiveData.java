package com.example.Horse_App.Database.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.Horse_App.Database.Entity.CourseEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CourseListLiveData extends LiveData<List<CourseEntity>> {

    private static final String TAG = "CourseListLiveData";
    private final DatabaseReference reference;
    private final MyValueEventListener listener = new MyValueEventListener();

    public CourseListLiveData(DatabaseReference reference) {
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
            setValue( toCourseList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<CourseEntity> toCourseList(DataSnapshot snapshot) {
        List<CourseEntity> cours = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            CourseEntity entity = childSnapshot.getValue(CourseEntity.class);
            entity.setCourseID(childSnapshot.getKey());
            cours.add(entity);
        }
        return cours;
    }
}
