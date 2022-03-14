package com.example.Horse_App.Database.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "course", foreignKeys = @ForeignKey(entity=Ride.class, parentColumns = "rideID", childColumns = "rideID"))
public class Course {

    @PrimaryKey(autoGenerate = true)
    public int courseID;

    @ColumnInfo(name = "rideID")
    public int rideID;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "price")
    public double price;

    public Course(int rideID, String date, double price) {
        this.rideID = rideID;
        this.date = date;
        this.price = price;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
