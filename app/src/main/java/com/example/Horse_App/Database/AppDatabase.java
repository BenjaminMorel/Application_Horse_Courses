package com.example.Horse_App.Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.Horse_App.Database.DAO.CourseDao;
import com.example.Horse_App.Database.DAO.RideDao;
import com.example.Horse_App.Database.DAO.UserDao;
import com.example.Horse_App.Database.Entity.Course;
import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.Database.Entity.User;

import java.util.concurrent.Executors;


@Database(entities = {User.class , Ride.class, Course.class}, version=9,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = "AppDatabase";
    private static AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "Horse-App";

    public abstract UserDao userDao() ;
    public abstract RideDao rideDao();
    public abstract CourseDao courseDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();




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

    private static AppDatabase buildDatabase(final Context appContext) {
        Log.i(TAG, "Database will be initialized.");
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(() -> {
                            AppDatabase database = AppDatabase.getAppDateBase(appContext);
                            initializeDemoData(database);
                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        });
                    }
                }).build();
    }

    public static void initializeDemoData(final AppDatabase database) {
        Executors.newSingleThreadExecutor().execute(() -> {
            database.runInTransaction(() -> {
                Log.i(TAG, "Wipe database.");
                database.userDao().deleteAll();
                database.rideDao().deleteAll();
                database.courseDao().deleteAll();

                DatabaseInitializer.populateDatabase(database);
            });
        });
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            Log.i(TAG, "Database initialized.");
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

}
