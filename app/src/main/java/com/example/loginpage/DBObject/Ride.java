package com.example.loginpage.DBObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ride")
public class Ride {

    @PrimaryKey(autoGenerate = true)
    public int rideID;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name ="lenght")
    public double lenght;

    @ColumnInfo(name ="duration")
    public double duration;

    @ColumnInfo(name =  "difficulty")
    public int difficulty;

    @ColumnInfo(name = "location")
    public String location;

    //TODO ajouter les coordonés latLong pour le départ et la fin


    public Ride(String description, double lenght, double duration, int difficulty, String location) {
        this.description = description;
        this.lenght = lenght;
        this.duration = duration;
        this.difficulty = difficulty;
        this.location = location;
    }

    public int getRideID() {
        return rideID;
    }

    public void setRideID(int rideID) {
        this.rideID = rideID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        this.lenght = lenght;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
