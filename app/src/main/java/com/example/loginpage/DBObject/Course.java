package com.example.loginpage.DBObject;

public class Course {
    private int ID_COURSE;
    private double price;
    private String description;

    public int getID_COURSE() {
        return ID_COURSE;
    }

    public void setID_COURSE(int ID_COURSE) {
        this.ID_COURSE = ID_COURSE;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course(int ID_COURSE, double price, String description) {
        this.ID_COURSE = ID_COURSE;
        this.price = price;
        this.description = description;
    }
}
