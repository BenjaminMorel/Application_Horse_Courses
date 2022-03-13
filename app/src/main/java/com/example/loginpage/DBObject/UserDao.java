package com.example.loginpage.DBObject;

import androidx.room.Dao;
import androidx.room.Entity;
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

    @Query(("UPDATE user SET email = :email, first_name = :firstname, last_name = :lastname, address = :address, city = :city, npa = :npa WHERE userID = :ID "))
    void updateSpecificRow(int ID, String email, String firstname, String lastname, String address, String city, int npa);
    @Insert
    void insert(User user);

    @Update()
    void update(User user);


}
