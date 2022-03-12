package com.example.loginpage.DBObject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE userID = :id")
    User findByID(int id);

    @Insert
    void instert(User user);

    @Update
    void update(User user);
}
