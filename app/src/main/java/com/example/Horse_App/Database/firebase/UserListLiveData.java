package com.example.Horse_App.Database.firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.Horse_App.Database.Entity.UserEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class UserListLiveData extends LiveData<UserEntity> {

    private static final String TAG = "UserListLiveData";
    private final DatabaseReference reference;
    private final MyValueEventListener listener = new MyValueEventListener();

    public UserListLiveData(DatabaseReference reference) {
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
            setValue((UserEntity) toUserList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }

    private List<UserEntity> toUserList(DataSnapshot snapshot) {
        List<UserEntity> userEntities = new ArrayList<>();
        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
            UserEntity entity = childSnapshot.getValue(UserEntity.class);
            entity.setUserID(childSnapshot.getKey());
            userEntities.add(entity);
        }
        return userEntities;
    }
}
