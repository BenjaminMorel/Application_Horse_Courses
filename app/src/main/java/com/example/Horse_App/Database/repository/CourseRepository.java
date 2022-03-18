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

    public LiveData<List<Course>> getCourses(Application application){
        return ((BaseApp) application).getDatabase().courseDao().getAllCourses();
    }

    public LiveData<List<Course>> getCoursesByUser(Application application,int id){
        return ((BaseApp) application).getDatabase().courseDao().getAllcourseByUser(id);
    }

    public void deleteByID(Application application,int id){
         ((BaseApp) application).getDatabase().courseDao().deleteByID(id);
    }

}
