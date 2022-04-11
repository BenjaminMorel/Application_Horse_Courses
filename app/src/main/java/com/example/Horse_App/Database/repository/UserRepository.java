package com.example.Horse_App.Database.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.Horse_App.Database.Entity.UserEntity;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.firebase.UserListLiveData;
import com.example.Horse_App.Database.firebase.UserLiveData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {

    private static final String TAG = "UserRepository";
    private static UserRepository instance;

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }

    public UserListLiveData getAllUsers() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("users");
        return new UserListLiveData(reference);
    }

    public LiveData<UserEntity> getUserByID(final String userID) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("users")
                .child(userID);
        return new UserLiveData(reference);
    }

    public LiveData<UserEntity> getUserByEmail(final String email) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("users")
                .child(email);
        return new UserLiveData(reference);
    }

    public void signIn(final String email, final String password, final OnCompleteListener<AuthResult> listener) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener);
    }

    public void register(final UserEntity user, final OnAsyncEventListener callback) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                user.getEmail(),
                user.getPassword()
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                user.setUserID(FirebaseAuth.getInstance().getCurrentUser().getUid());
                insert(user, callback);
            } else {
                callback.onFailure(task.getException());
            }
        });
    }

    private void insert(final UserEntity user, final OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(user.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                        FirebaseAuth.getInstance().getCurrentUser().delete()
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        callback.onFailure(null);
                                        Log.d(TAG, "Rollback successful: User deleted");
                                    } else {
                                        callback.onFailure(task.getException());
                                        Log.d(TAG, "Rollback failed: signInWithEmail:failure",
                                                task.getException());
                                    }
                                });
                    } else {
                        callback.onSuccess();
                    }
                });
    }


    public void update(final UserEntity user, final OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("users")
                .child(user.getUserID())
                .updateChildren(user.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
        FirebaseAuth.getInstance().getCurrentUser().updatePassword(user.getPassword())
                .addOnFailureListener(
                        e -> Log.d(TAG, "updatePassword failure!", e)
                );
    }

}
