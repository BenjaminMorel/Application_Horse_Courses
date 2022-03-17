package com.example.Horse_App.Database.async.Course;

import android.app.Application;
import android.os.AsyncTask;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.Course;
import com.example.Horse_App.Database.Entity.User;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;

public class CreateCourse extends AsyncTask<Course, Void, Void> {

    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public CreateCourse(Application application, OnAsyncEventListener callback){
        this.application = application;
        this.callback = callback;
    }


    @Override
    protected Void doInBackground(Course... params) {
        try {
            for (Course course : params)
                ((BaseApp) application).getDatabase().courseDao()
                        .insert(course);
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
