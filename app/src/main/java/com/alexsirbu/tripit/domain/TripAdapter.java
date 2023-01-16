package com.alexsirbu.tripit.domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.alexsirbu.tripit.R;

import java.util.List;
import java.util.Objects;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder> {

    private final LiveData<List<Trip>> trips;

    public TripAdapter(LiveData<List<Trip>> trips) {
        this.trips = trips;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
        return new TripViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
        Trip trip = Objects.requireNonNull(trips.getValue()).get(position);
        holder.getTextViewTitle().setText(trip.getName());
        holder.getTextViewDestination().setText(trip.getDestination());
        holder.getTextViewPrice().setText(String.format("%s",trip.getPrice()));
    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(trips.getValue()).size();
    }
}
