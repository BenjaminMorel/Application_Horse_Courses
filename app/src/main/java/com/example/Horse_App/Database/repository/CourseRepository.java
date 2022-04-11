package com.example.Horse_App.Database.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.Horse_App.Database.Entity.CourseEntity;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.firebase.CourseListLiveData;
import com.example.Horse_App.Database.firebase.CourseLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CourseRepository {

    private static final String TAG = "CourseRepository";
    private static CourseRepository instance;

    private CourseRepository() {

    }

    public static CourseRepository getInstance() {
        if (instance == null) {
            synchronized (CourseRepository.class) {
                if (instance == null) {
                    instance = new CourseRepository();
                }
            }
        }
        return instance;
    }

    public CourseListLiveData getAllCourses() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("courses");
        return new CourseListLiveData(reference);
    }

    public LiveData<CourseEntity> getCoursesByUserId(final String userId) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("courses")
                .child(userId);
        return new CourseLiveData(reference);
    }

    private void insert(final CourseEntity courseEntity, final OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("courses")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(courseEntity, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                        FirebaseAuth.getInstance().getCurrentUser().delete()
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        callback.onFailure(null);
                                        Log.d(TAG, "Rollback successful: Course deleted");
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

    public void delete(final CourseEntity courseEntity, OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("courses")
                .child(courseEntity.getCourseID())
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }

}
