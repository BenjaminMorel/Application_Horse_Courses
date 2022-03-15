package com.example.Horse_App.Database.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.Horse_App.Database.Entity.Ride;

import java.util.List;

@Dao
public interface RideDao {

    @Query("SELECT * FROM ride")
    List<Ride> getAll();

    @Query("SELECT * FROM ride WHERE rideID = :id")
    Ride getByID(int id);

    @Insert()
    void instert(Ride ride);

    @Query("DELETE FROM ride")
    void deleteAll();

}

