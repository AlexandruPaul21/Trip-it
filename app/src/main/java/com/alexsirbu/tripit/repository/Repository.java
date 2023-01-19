package com.alexsirbu.tripit.repository;

import androidx.lifecycle.LiveData;

import com.alexsirbu.tripit.domain.GenericEntity;
import com.alexsirbu.tripit.domain.Trip;

import java.util.List;

public interface Repository<ID, E extends GenericEntity<ID>> {
    E findOne(ID id);
    LiveData<List<E>> findAll();
    void save(E entity);
    void delete(Trip trip);
    void update(Trip trip);
}
