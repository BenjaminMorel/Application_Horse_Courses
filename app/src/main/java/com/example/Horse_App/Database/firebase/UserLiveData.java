package com.example.Horse_App.Database.firebase;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import com.example.Horse_App.Database.Entity.UserEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class UserLiveData extends LiveData<UserEntity> {

    private static final String TAG = "UserLiveData";
    private final DatabaseReference reference;
    private final MyValueEventListener listener = new MyValueEventListener();

    public UserLiveData(DatabaseReference ref) {
        reference = ref;
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
            UserEntity entity = dataSnapshot.getValue(UserEntity.class);
            entity.setUserID(dataSnapshot.getKey());
            setValue(entity);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(TAG, "Can't listen to query " + reference, databaseError.toException());
        }
    }
}
