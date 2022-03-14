package com.example.Horse_App.Database.async.User;

import android.app.Application;
import android.os.AsyncTask;

import com.example.Horse_App.BaseApp;
import com.example.Horse_App.Database.Entity.User;
import com.example.Horse_App.Database.Util.OnAsyncEventListener;

public class DeleteUser extends AsyncTask<User,Void,Void> {

    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public DeleteUser(Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
    }
    @Override
    protected Void doInBackground(User... params) {
        try {
            for (User user : params)
                ((BaseApp) application).getDatabase().userDao()
                        .delete(user);
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
