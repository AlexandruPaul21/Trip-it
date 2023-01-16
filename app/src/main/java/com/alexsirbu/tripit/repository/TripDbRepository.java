package com.alexsirbu.tripit.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.alexsirbu.tripit.domain.Trip;
import com.alexsirbu.tripit.domain.TripDao;
import com.alexsirbu.tripit.domain.TripRoomDatabase;

import java.util.List;

public class TripDbRepository implements Repository<Long, Trip> {

    private TripDao tripDao;
    private LiveData<List<Trip>> trips;

    public TripDbRepository(Application application) {
        TripRoomDatabase tripRoomDatabase = TripRoomDatabase.getDatabase(application);
        tripDao = tripRoomDatabase.tripDao();
        trips = tripDao.getTrips();
    }

//    @Override
//    public Trip findOne(Long aLong) {
//        TripRoomDatabase.databaseThread.execute(() -> {
//            tripDao.findById(aLong);
//        });
//    }

    @Override
    public LiveData<List<Trip>> findAll() {
        return trips;
    }

    @Override
    public void save(Trip entity) {
        TripRoomDatabase.databaseThread.execute(() -> {
            tripDao.insert(entity);
        });
    }

    @Override
    public void delete(Trip trip) {
        TripRoomDatabase.databaseThread.execute(() -> {
            tripDao.delete(trip);
        });
    }

    @Override
    public void update(Trip trip) {
        TripRoomDatabase.databaseThread.execute(() -> {
            tripDao.update(trip);
        });
    }
}
