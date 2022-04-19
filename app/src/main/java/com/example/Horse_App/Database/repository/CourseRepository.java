package com.example.Horse_App.Database.repository;


import androidx.lifecycle.LiveData;
import com.example.Horse_App.Database.Entity.CourseEntity;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.firebase.CourseListLiveData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;

public class CourseRepository {

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

    public LiveData<List<CourseEntity>> getCoursesByUserId(final String userId) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("courses")
                .child(userId)
                .orderByChild("date").getRef();
        return new CourseListLiveData(reference);
    }

    public void insert(final CourseEntity course, final OnAsyncEventListener callback) {
        String id = FirebaseDatabase.getInstance().getReference("courses").push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("courses")
                .child(FirebaseAuth.getInstance().getUid())
                .child(id)
                .setValue(course.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }

    public void delete(final String courseID, OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("courses")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(courseID)
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }
}
