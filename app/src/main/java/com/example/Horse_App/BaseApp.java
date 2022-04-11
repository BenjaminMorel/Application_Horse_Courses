package com.example.Horse_App;

import android.app.Application;

import com.example.Horse_App.Database.repository.CourseRepository;
import com.example.Horse_App.Database.repository.RideRepository;
import com.example.Horse_App.Database.repository.UserRepository;

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public UserRepository getUserRepository(){ return UserRepository.getInstance(); }

    public RideRepository getRideRepository(){ return RideRepository.getInstance(); }
    public CourseRepository getCourseRepository(){return CourseRepository.getInstance();}
}
