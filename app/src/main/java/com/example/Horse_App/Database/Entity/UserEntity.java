package com.example.Horse_App.Database.Entity;


import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UserEntity {

    public String userID;
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public String phoneNumber;

    public UserEntity(String email, String password, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public UserEntity() {
    }

    @Exclude
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Exclude
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals( Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof UserEntity)) return false;
        UserEntity o = (UserEntity) obj;
        return o.getEmail().equals(this.getEmail());
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("firstName", firstName);
        result.put("lastName", lastName);
        result.put("phoneNumber", phoneNumber);
        return result;
    }
}
