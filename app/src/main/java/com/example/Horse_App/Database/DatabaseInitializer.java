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
                                final String lastname, final String phoneNumber) {
        User user = new User(email, password, firstname, lastname, phoneNumber);
        db.userDao().insert(user);
    }

    private static void addRide(final AppDatabase db, final String description, final double length, final double duration, final int difficulty,
                                final String location, final String positions, final String time, final double price,final String path) {
        Ride ride = new Ride(description, length, duration, difficulty, location, positions, time, price,path);
        db.rideDao().insert(ride);
    }


    private static void populateWithTestData(AppDatabase db) {

        db.userDao().deleteAll();
        addUser(db, "admin", "123", "Hugo", "Vouillamoz", "075248621");
        addUser(db, "admin2", "123", "Benjamin", "Morel", "078689289");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        db.rideDao().deleteAll();
        addRide(db, "Super balade", 10.2, 3.5, 4, "Martigny", "46.109987/7.102460/46.122314/7.129649/46.119117/7.132502/46.107869/7.103133", "13:30/16:30", 45.50,"@drawable/martigny");
        addRide(db, "Super promenade", 9.8, 4.8, 3, "Liddes", "45.981136/7.189659/45.972979/7.202241/45.973308/7.207658/45.987731/7.189178", "11:00/14:15", 32.50,"@drawable/liddes");
        addRide(db, "Super excursion", 7.8, 2.5, 1, "Saillon", "46.168166/7.177144/46.167106/7.171189/46.161594/7.165012/46.157026/7.155829/46.164446/7.180595", "12:00/15:00", 40.00,"@drawable/saillon");
        addRide(db, "Balade au bord du Rhone", 7.6, 2.5, 2, "Sierre", "46.3011113/7.565139/46.304852/7.578500/46.307543/7.579149/46.304661/7.567815", "14:30/16:30", 25.60, "");
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
