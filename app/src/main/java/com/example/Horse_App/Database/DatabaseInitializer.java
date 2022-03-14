package com.example.Horse_App.Database;

import android.os.AsyncTask;
import android.util.Log;

import com.example.Horse_App.Database.Entity.Ride;
import com.example.Horse_App.Database.Entity.User;

public class DatabaseInitializer {

    public static final String TAG = "DatabaseInitializer";

    public static void populateDatabase(final AppDatabase db) {
        Log.i(TAG, "Inserting demo data.");
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static void addUser(final AppDatabase db, final String email, final String password, final String firstname,
                                final String lastname, final String phoneNumber){
        User user = new User(email,password,firstname,lastname,phoneNumber);
        db.userDao().insert(user);
    }

    private static void addRide(final AppDatabase db, final String description, final double lenght, final double duration, final int difficulty, final String location){
        Ride ride = new Ride(description,lenght,duration,difficulty,location);
        db.rideDao().instert(ride);
    }


    private static void populateWithTestData(AppDatabase db) {
        db.userDao().deleteAll();

        addUser(db, "admin","123","Hugo","Vouillamoz", "075248621");
        addUser(db, "admin","123","Benjmain","Morel", "078689289");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        db.rideDao().deleteAll();

        addRide(db, "Super balade", 10.2, 3.5, 4, "Martigny");
        addRide(db, "Super promenade", 9.8, 4.8, 3, "Sierre");
        addRide(db, "Super excursion", 7.8, 2.5, 1, "Sion");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final AppDatabase database;

    PopulateDbAsync(AppDatabase db) {
        database = db;
    }

    @Override
    protected Void doInBackground(final Void... params) {
        populateWithTestData(database);
        return null;
    }

}

}
