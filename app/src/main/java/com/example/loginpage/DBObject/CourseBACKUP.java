package com.example.loginpage.DBObject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

public class CourseBACKUP {



    private String city;
    private int difficulty;
    private double duration;
    private double length;
    private double price;
    private String description;


    public CourseBACKUP(String city, int difficulty, double duration, double length, double price, String description) {
        this.city = city;
        this.difficulty = difficulty;
        this.duration = duration;
        this.length = length;
        this.price = price;
        this.description = description;
    }


    public String getCity() {
        return city;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public double getDuration() {
        return duration;
    }

    public double getLength() {
        return length;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
