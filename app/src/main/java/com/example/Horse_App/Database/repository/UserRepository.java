package com.example.Horse_App.Database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.User;

import java.util.List;

public class UserRepository {

    private static UserRepository instance;

    private UserRepository(){

    }

    public static UserRepository getInstance(){
        if(instance == null){
            synchronized (UserRepository.class){
                if(instance == null){
                    instance = new UserRepository();
                }
            }
        }
        return instance;
    }


    public LiveData<List<User>> getUsers(Application application){
        return ((BaseApp) application).getDatabase().userDao().getAll();
    }

    public LiveData<User> getUserByID(final int userID, Application application){
        return ((BaseApp) application).getDatabase().userDao().getByID(userID);
    }

    public LiveData<User> getUserByEmail(final String email, Application application){
        return ((BaseApp) application).getDatabase().userDao().getByEmail(email);
    }


}
