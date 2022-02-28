package com.example.loginpage.DataBase;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();
}
