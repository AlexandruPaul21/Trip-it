package com.alexsirbu.tripit.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alexsirbu.tripit.domain.Trip;
import com.alexsirbu.tripit.repository.TripDbRepository;

import java.util.List;

public class TripViewModel extends AndroidViewModel {
    private TripDbRepository tripRepository;
    private LiveData<List<Trip>> trips;

    public TripViewModel(@NonNull Application application) {
        super(application);
        tripRepository = new TripDbRepository(application);
        trips =  tripRepository.findAll();
    }

    public void insert(Trip trip) {
        tripRepository.save(trip);
    }

    public void update(Trip trip) {
        tripRepository.update(trip);
    }

    public void delete(Trip trip) {
        tripRepository.delete(trip);
    }

    public LiveData<List<Trip>> getTrips() {
        return trips;
    }
}
