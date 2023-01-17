package com.alexsirbu.tripit.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alexsirbu.tripit.EditActivity;
import com.alexsirbu.tripit.R;
import com.alexsirbu.tripit.RecyclerItemClickListener;
import com.alexsirbu.tripit.domain.Trip;
import com.alexsirbu.tripit.domain.TripAdapter;
import com.alexsirbu.tripit.domain.Types;
import com.alexsirbu.tripit.models.TripViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                        intent.putExtra("position", position);
                        startActivity(intent);
                    }
                })
        );

//        Trip trip = new Trip(1L, "Warsaw city", "Warsaw",
//                Types.CITY_BREAK, 20.5F, "20/10/2022 18:00",
//                "24/10/2022 18:00", 5);


        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        tripsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //tripsRecyclerView.setAdapter(new TripAdapter(tripViewModel.getTrips()));

        tripViewModel.getTrips().observe(getViewLifecycleOwner(), trips -> tripsRecyclerView.setAdapter(new TripAdapter(tripViewModel.getTrips())));

        //System.out.println(tripViewModel.getTrips().getValue());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}