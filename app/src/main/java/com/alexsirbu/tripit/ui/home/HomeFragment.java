package com.alexsirbu.tripit.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexsirbu.tripit.EditActivity;
import com.alexsirbu.tripit.R;
import com.alexsirbu.tripit.RecyclerItemClickListener;
import com.alexsirbu.tripit.domain.Trip;
import com.alexsirbu.tripit.domain.TripAdapter;
import com.alexsirbu.tripit.domain.TripRoomDatabase;
import com.alexsirbu.tripit.domain.Types;
import com.alexsirbu.tripit.models.TripViewModel;

public class HomeFragment extends Fragment {

    private TripViewModel tripViewModel;
    private RecyclerView tripsRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tripsRecyclerView = view.findViewById(R.id.tripsRecyclerView);

        tripsRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), tripsRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.e("Test", "yes");
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), EditActivity.class);
                        intent.putExtra("position", tripViewModel.getPos(position));
                        startActivity(intent);
                    }
                })
        );

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        tripViewModel.getTrips().observe(getViewLifecycleOwner(), trips -> {
                    tripsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    tripsRecyclerView.setAdapter(new TripAdapter(this, getContext(), tripViewModel.getTrips()));
                }
        );

        TripRoomDatabase.databaseThread.execute(() -> {
            if (tripViewModel.getTrips().getValue() == null) {
                tripViewModel.insert(new Trip(1L, "Warsaw city", "Warsaw",
                        Types.CITY_BREAK, 20.5F, "20/10/2022",
                        "24/10/2022", 5));
                tripViewModel.insert(new Trip(2L, "Two year together", "Ibiza",
                        Types.SEA_SIDE, 100.25F, "15/07/2022",
                        "25/07/2022", 3));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}