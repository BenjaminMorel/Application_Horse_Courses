package com.example.Horse_App.Database.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.util.TableInfo;

import java.util.Date;

@Entity(tableName = "course",
        foreignKeys = {@ForeignKey(entity = Ride.class, parentColumns = "rideID", childColumns = "rideID", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = User.class, parentColumns = "userID", childColumns = "userID", onDelete = ForeignKey.CASCADE)})
public class Course {

    @PrimaryKey(autoGenerate = true)
    public int courseID;

    @ColumnInfo(name = "rideID")
    public int rideID;

    @ColumnInfo(name = "userID")
    public int userID;

    @ColumnInfo(name = "date")
    public String date;

    public Course(int rideID, int userID, String date) {
        this.rideID = rideID;
        this.userID = userID;
        this.date = date;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getRideID() {
        return rideID;
    }

    public void setRideID(int rideID) {
        this.rideID = rideID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
