package com.example.Horse_App.Database.Util;

/**
 * This generic interface is used as custom callback for async tasks.
 */
public interface OnAsyncEventListener {
    void onSuccess();
    void onFailure(Exception e);
}
