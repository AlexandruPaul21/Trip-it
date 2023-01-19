package com.alexsirbu.tripit.domain;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Trip.class},
        version = 2)
public abstract class TripRoomDatabase extends RoomDatabase {

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE trips"
                    + " ADD COLUMN favourite INTEGER NOT NULL DEFAULT(0) ");
        }
    };

    public abstract TripDao tripDao();

    private static TripRoomDatabase INSTANCE;

    public static TripRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    TripRoomDatabase.class,
                    "trips_database").addMigrations(MIGRATION_1_2).build();
        }
        return INSTANCE;
    }

    public static ExecutorService databaseThread = Executors.newFixedThreadPool(4);

}
