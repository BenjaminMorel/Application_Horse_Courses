package com.example.Horse_App.Database.async.Course;

import android.app.Application;
import android.os.AsyncTask;

import com.example.Horse_App.Database.Entity.CourseEntity;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;

public class DeleteCourse extends AsyncTask<CourseEntity, Void, Void> {

    private final Application application;
    private final OnAsyncEventListener callback;
    private Exception exception;

    public DeleteCourse(Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(CourseEntity... params) {
        try {
//            for (Course course : params) {
//                ((BaseApp) application).getDatabase().courseDao()
//                        .deleteByID(course.courseID);
//                System.out.println("Delete course " + course.courseID + " succes");
//            }
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (callback != null) {
            if (exception == null) {
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }
    }
}
