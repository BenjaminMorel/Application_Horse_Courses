package com.example.loginpage.DBObject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CourseDao {

    @Query("SELECT * FROM course")
    List<Course> getAllCourse();

    @Insert()
    void instert(Course course);
}