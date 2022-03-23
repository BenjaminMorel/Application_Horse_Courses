package com.example.Horse_App.Database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.Course;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;
import com.example.Horse_App.Database.async.Course.CreateCourse;

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

    public LiveData<List<Course>> getAllCourses(Application application) {
        return ((BaseApp) application).getDatabase().courseDao().getAllCourses();
    }

    public List<Course> getCoursesByUser(Application application, int id) {
        return ((BaseApp) application).getDatabase().courseDao().getAllCoursesByUser(id);
    }

    public void insert(final Course course, OnAsyncEventListener callback,
                       Application application) {
        new CreateCourse(application, callback).execute(course);
    }

    public void deleteByID(Application application, int id) {
        ((BaseApp) application).getDatabase().courseDao().deleteByID(id);
    }

}
