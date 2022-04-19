package com.example.Horse_App.Database.Entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class RideEntity {

    private String rideID;
    private String description;
    private double length;
    private double duration;
    private int difficulty;
    private String location;
    private String positions;
    private String time;
    private double price;
    private String picturePath;

    public RideEntity(String description, double length, double duration, int difficulty, String location, String positions, String time, double price, String picturePath) {
        this.description = description;
        this.length = length;
        this.duration = duration;
        this.difficulty = difficulty;
        this.location = location;
        this.positions = positions;
        this.time = time;
        this.price = price;
        this.picturePath = picturePath;
    }

    public RideEntity() {
    }

    public String getRideID() {
        return rideID;
    }

    public void setRideID(String rideID) {
        this.rideID = rideID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
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

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("description", description);
        result.put("length", length);
        result.put("duration", duration);
        result.put("difficulty", difficulty);
        result.put("location", location);
        result.put("positions", positions);
        result.put("time", time);
        result.put("price", price);
        result.put("picturePath", picturePath);
        return result;
    }
}
