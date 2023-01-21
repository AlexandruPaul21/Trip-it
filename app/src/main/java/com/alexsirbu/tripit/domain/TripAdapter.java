package com.alexsirbu.tripit.domain;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import com.alexsirbu.tripit.R;
import com.alexsirbu.tripit.ReadOnlyActivity;
import com.alexsirbu.tripit.models.TripViewModel;
import com.alexsirbu.tripit.utils.RandomPhotoGenerator;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder>{

    private final TripViewModel tripViewModel;
    private final LiveData<List<Trip>> trips;
    private final Context context;

    public TripAdapter(ViewModelStoreOwner owner, Context context, LiveData<List<Trip>> trips) {
        this.context = context;
        this.trips = trips;
        tripViewModel = new ViewModelProvider(owner).get(TripViewModel.class);
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
        holder.setId(trip.getId());
        holder.getTextViewTitle().setText(trip.getName());
        holder.getTextViewDestination().setText(trip.getDestination());
        holder.getTextViewPrice().setText(String.format("%s $",trip.getPrice()));
        holder.getFavouriteButton().setOnClickListener(view -> {
            trip.setFavourite(!trip.getFavourite());
            if (trip.getFavourite()) {
                holder.getFavouriteButton().setImageResource(R.drawable.ic_favourite_red_24);
            } else {
                holder.getFavouriteButton().setImageResource(R.drawable.ic_favourite_black_24);
            }
        });
        holder.getReadOnlyButton().setOnClickListener((View.OnClickListener)view -> {
            Intent intent = new Intent(view.getContext(), ReadOnlyActivity.class);
            intent.putExtra("position", holder.getId());
            view.getContext().startActivity(intent);
        });
        Picasso.get().load(RandomPhotoGenerator.generate())
                .placeholder(R.drawable.gallery)
                .into(holder.getImageViewPhoto());
    }

    @Override
    public int getItemCount() {
        if (trips.getValue() == null) {
            return 0;
        }
        return trips.getValue().size();
    }


}
