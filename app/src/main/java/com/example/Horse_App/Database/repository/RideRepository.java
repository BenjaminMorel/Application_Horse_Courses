package com.example.Horse_App.Database.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.Ride;

import java.util.List;

public class RideRepository {

    private static RideRepository instance;

    private RideRepository() {
    }

    public static RideRepository getInstance() {
        if (instance == null) {
            synchronized (RideRepository.class) {
                if (instance == null) {
                    instance = new RideRepository();
                }
            }
        }
        return instance;
    }

    public List<Ride> getRides(Application application) {
        return ((BaseApp) application).getDatabase().rideDao().getAll();
    }

    public Ride getRide(final int id, Application application) {
        return ((BaseApp) application).getDatabase().rideDao().getByID(id);
    }
}
