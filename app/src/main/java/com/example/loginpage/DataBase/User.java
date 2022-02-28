package com.example.loginpage.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int ID_User;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name= "address")
    public String address;
    @ColumnInfo(name ="phoneNumber")
    public String phoneNumber;

    @ColumnInfo(name= "email")
    public String email;

    @ColumnInfo(name ="password")
    public String password;



}
