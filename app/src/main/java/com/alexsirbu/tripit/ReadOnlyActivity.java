package com.alexsirbu.tripit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alexsirbu.tripit.domain.Trip;
import com.alexsirbu.tripit.domain.TripRoomDatabase;
import com.alexsirbu.tripit.domain.Types;
import com.alexsirbu.tripit.models.TripViewModel;
import com.alexsirbu.tripit.network.OnGetWeatherCallback;
import com.alexsirbu.tripit.network.WeatherRepository;
import com.alexsirbu.tripit.network.WeatherWrapper;

import java.util.List;

public class ReadOnlyActivity extends AppCompatActivity {
    private WeatherRepository weatherRepository;
    private TripViewModel tripViewModel;

    private Trip trip;

    private EditText editTextTitle;
    private EditText editTextLocation;
    private EditText editTextType;
    private EditText editTextPrice;
    private EditText editTextPeriod;
    private EditText editTextRating;
    private EditText editTextLatitude;
    private EditText editTextLongitude;

    private TextView textViewWeather;
    private Button buttonWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_only);

        setupViews();

        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        TripRoomDatabase.databaseThread.execute(() -> {
            extractTrip();
            populateFields();
        });

        setupButton();
    }

    public void getWeather(Float lat, Float lon) {
        weatherRepository = WeatherRepository.getInstance();
        weatherRepository.getWeather(new OnGetWeatherCallback() {
            @Override
            public void onSuccess(List<WeatherWrapper> weatherWrappers) {
                Log.e("API", weatherWrappers.toString());
            }

            @Override
            public void onError() {
                Log.e("API", "fail");
            }
        });
    }

    private void setupButton() {
        buttonWeather.setOnClickListener(view -> {
            TripRoomDatabase.databaseThread.execute(() -> {
                getWeather(20F,20F);
            });
        });
    }

    private void populateFields() {
        editTextTitle.setText(trip.getName());
        editTextLocation.setText(trip.getDestination());
        if (trip.getType() == Types.CITY_BREAK) {
            editTextType.setText("City Break");
        } else if (trip.getType() == Types.MOUNTAINS) {
            editTextType.setText("Mountains");
        } else {
            editTextType.setText("Sea side");
        }
        editTextPrice.setText(String.format("%s",trip.getPrice()));
        editTextPeriod.setText(String.format("%s -> %s",trip.getStartDateTime(), trip.getEndDateTime()));
        editTextRating.setText(String.format("%s stars", trip.getRating()));
    }

    private void extractTrip() {
        Bundle bundle = getIntent().getExtras();
        Long id = bundle.getLong("position");
        trip = new Trip();
        trip = tripViewModel.get(id);
    }

    private void setupViews() {
        editTextTitle = findViewById(R.id.editTextTitleRo);
        editTextLocation = findViewById(R.id.editTextLocationRo);
        editTextType = findViewById(R.id.editTextTripTypeRo);
        editTextPrice = findViewById(R.id.editTextPriceRo);
        editTextPeriod = findViewById(R.id.editTextPeriod);
        editTextRating = findViewById(R.id.editTextRating);
        editTextLatitude = findViewById(R.id.editTextLatitude);
        editTextLongitude = findViewById(R.id.editTextLongitude);
        textViewWeather = findViewById(R.id.textViewWeather);
        buttonWeather = findViewById(R.id.buttonWeather);
    }
}