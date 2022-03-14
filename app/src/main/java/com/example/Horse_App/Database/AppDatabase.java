package com.example.Horse_App.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.Horse_App.Database.DAO.CourseDao;
import com.example.Horse_App.Database.DAO.RideDao;
import com.example.Horse_App.Database.DAO.UserDao;
import com.example.Horse_App.Database.Entity.Course;
import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.Database.Entity.User;


@Database(entities = {User.class , Ride.class, Course.class}, version=5,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao() ;
    public abstract RideDao rideDao();
    public abstract CourseDao courseDao();

    public static  AppDatabase getAppDateBase(Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "AppDatabase")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
