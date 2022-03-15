package com.example.Horse_App.Database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.Course;

import java.util.List;

public class CourseRepository {

    private static CourseRepository instance ;

    private CourseRepository(){

    }

    public static CourseRepository getInstance(){
        if(instance ==  null){
            synchronized (CourseRepository.class){
                if(instance == null){
                    instance = new CourseRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<List<Course>> getCourse(Application application){
        return ((BaseApp) application).getDatabase().courseDao().getAllCourse();
    }

}
