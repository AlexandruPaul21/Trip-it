package com.alexsirbu.tripit.domain;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Trip trip);

    @Update
    void update(Trip trip);

    @Delete
    void delete(Trip trip);

    @Query("SELECT * FROM trips ORDER BY start")
    LiveData<List<Trip>> getTrips();

    @Query("SELECT * FROM trips WHERE id = :id")
    Trip findById(Long id);

    @Query("SELECT * FROM trips")
    List<Trip> getLowestFreeId();
}
