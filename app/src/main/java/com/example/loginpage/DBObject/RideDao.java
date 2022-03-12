package com.example.loginpage.DBObject;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RideDao {

    @Query("SELECT * FROM ride")
    List<Ride> getAll();

    @Insert()
    void instert(Ride ride);

}

