package com.alexsirbu.tripit.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.alexsirbu.tripit.domain.Trip;
import com.alexsirbu.tripit.domain.TripDao;
import com.alexsirbu.tripit.domain.TripRoomDatabase;
import com.alexsirbu.tripit.domain.Types;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TripDbRepository implements Repository<Long, Trip> {

    private TripDao tripDao;
    private LiveData<List<Trip>> trips;

    public TripDbRepository(Application application) {
        TripRoomDatabase tripRoomDatabase = TripRoomDatabase.getDatabase(application);
        tripDao = tripRoomDatabase.tripDao();
        trips = tripDao.getTrips();
    }

    @Override
    public Trip findOne(Long aLong) {
        return tripDao.findById(aLong);
    }



    @Override
    public LiveData<List<Trip>> findAll() {
        return trips;
    }

    @Override
    public void save(Trip entity) {
        tripDao.insert(entity);
    }

    @Override
    public void delete(Trip trip) {
        tripDao.delete(trip);
    }

    @Override
    public void update(Trip trip) {
        tripDao.update(trip);
    }

    public Long getNewId() {
        return (long)tripDao.getLowestFreeId().size()+1;
    }
}
