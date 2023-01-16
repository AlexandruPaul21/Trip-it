package com.alexsirbu.tripit.domain;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Trip.class}, version = 1)
public abstract class TripRoomDatabase extends RoomDatabase {

    public abstract TripDao tripDao();

    private static TripRoomDatabase INSTANCE;

    public static TripRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    TripRoomDatabase.class,
                    "trips_database").build();
        }
        return INSTANCE;
    }

    public static ExecutorService databaseThread = Executors.newFixedThreadPool(4);

}
