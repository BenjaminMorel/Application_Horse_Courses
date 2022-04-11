package com.example.Horse_App.Database.Entity;

import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

public class CourseEntity {

    private String courseID;
    private String rideID;
    private String userID;
    private String date;

    public CourseEntity(String rideID, String userID, String date) {
        this.rideID = rideID;
        this.userID = userID;
        this.date = date;
    }

    public CourseEntity() {
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getRideID() {
        return rideID;
    }

    public void setRideID(String rideID) {
        this.rideID = rideID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userID", userID);
        result.put("rideID", rideID);
        result.put("date", date);
        return result;
    }
}