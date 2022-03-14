package com.example.Horse_App.Database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.Horse_App.Database.Entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user WHERE userID = :id")
    LiveData<User> getByID(int id);

    @Query(("UPDATE user SET email = :email, first_name = :firstname, last_name = :lastname, phoneNumber = :phoneNumber WHERE userID = :ID "))
    void updateSpecificRow(int ID, String email, String firstname, String lastname, String phoneNumber);

    @Insert
    void insert(User user);

    @Update()
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void deleteAll();
}
