package com.example.Horse_App.Database.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.Horse_App.Database.Entity.Course;

import java.util.List;

@Dao
public interface CourseDao {

    @Query("SELECT * FROM course")
    LiveData<List<Course>> getAllCourses();

    @Query("SELECT * FROM course WHERE userID= :id")
    List<Course> getAllCoursesByUser(int id);

    @Query("DELETE FROM course")
    void deleteAll();

    @Query("DELETE FROM course WHERE courseID=:id")
    void deleteByID(int id);

    @Insert
    void insert(Course course);
}