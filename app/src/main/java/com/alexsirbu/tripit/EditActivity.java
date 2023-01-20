package com.alexsirbu.tripit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alexsirbu.tripit.domain.Trip;
import com.alexsirbu.tripit.domain.TripDao;
import com.alexsirbu.tripit.domain.TripRoomDatabase;
import com.alexsirbu.tripit.domain.TripViewHolder;
import com.alexsirbu.tripit.domain.Types;
import com.alexsirbu.tripit.models.TripViewModel;

import java.util.Calendar;
import java.util.Objects;

public class EditActivity extends AppCompatActivity {
    private TripViewModel tripViewModel;
    private Trip trip;

    private EditText editTextTitle;
    private EditText editTextLocation;

    private RadioGroup radioGroupType;

    private TextView priceTextView;
    private SeekBar seekBarPrice;

    private EditText editTextStartDate;
    private EditText editTextEndDate;

    private RatingBar ratingBar;

    private Button deleteBtn;
    private Button cancelBtn;
    private Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setupViews();

        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        TripRoomDatabase.databaseThread.execute(() -> {
            extractTrip();
            populateFields();
        });
    }

    private void populateFields() {
        editTextTitle.setText(trip.getName());
        editTextLocation.setText(trip.getDestination());
        if (trip.getType() == Types.CITY_BREAK) {
            radioGroupType.check(R.id.cityBreak);
        } else if (trip.getType() == Types.MOUNTAINS) {
            radioGroupType.check(R.id.mountains);
        } else {
            radioGroupType.check(R.id.seaSide);
        }
        seekBarPrice.setProgress(trip.getPrice().intValue());
        editTextStartDate.setText(trip.getStartDateTime());
        editTextEndDate.setText(trip.getEndDateTime());
        ratingBar.setRating((float) trip.getRating());
    }

    private void extractTrip() {
        Bundle bundle = getIntent().getExtras();
        Long id = bundle.getLong("position");
        trip = new Trip();
        trip = tripViewModel.get(id);
    }

    private void setupViews() {
        editTextTitle = findViewById(R.id.editTextTitleE);
        editTextLocation = findViewById(R.id.editTextLocationE);

        radioGroupType = findViewById(R.id.radioGroupTypeE);

        priceTextView = findViewById(R.id.textViewPrice2E);
        seekBarPrice = findViewById(R.id.seekBarPriceE);

        setupSeekBar();

        editTextStartDate = findViewById(R.id.editTextStartDateE);
        editTextEndDate = findViewById(R.id.editTextEndDateE);

        ratingBar = findViewById(R.id.ratingBarTripRatingE);

        saveBtn = findViewById(R.id.buttonSaveE);
        cancelBtn = findViewById(R.id.buttonCancelE);
        deleteBtn = findViewById(R.id.buttonDeleteE);

        setupButtons();
    }

    private void setupButtons() {
        startDatePicker();
        endDatePicker();
        cancelDeleteButton();
        saveButtonHandle();
    }

    private void cancelDeleteButton() {
        cancelBtn.setOnClickListener(view -> {
            this.finish();
        });
        deleteBtn.setOnClickListener(view -> {
            TripRoomDatabase.databaseThread.execute(() -> {
                tripViewModel.delete(trip);
            });
            this.finish();
        });
    }

    private void saveButtonHandle() {
        saveBtn.setOnClickListener(view -> {
            boolean valid = true;
            String title = editTextTitle.getText().toString();
            if (title.equals("")) {
                editTextTitle.setError("Title cannot be void");
                editTextTitle.setText("");
                valid = false;
            }

            String location = editTextLocation.getText().toString();
            if (location.equals("")) {
                editTextLocation.setError("Location cannot be void");
                editTextLocation.setText("");
                valid = false;
            }

            int type = radioGroupType.getCheckedRadioButtonId();
            if (type == -1) {
                valid = false;
            }

            int price = seekBarPrice.getProgress();
            if (price == 0) {
                valid = false;
            }

            String startDate = editTextStartDate.getText().toString();

            String endDate = editTextEndDate.getText().toString();
            if (startDate.equals("") || endDate.equals("")) {
                valid = false;
            }

            float rating = ratingBar.getRating();

            RadioButton button = findViewById(type);

            Types tripType;

            if (button.getText().equals("Sea side")) {
                tripType = Types.SEA_SIDE;
            } else if (button.getText().equals("Mountains")) {
                tripType = Types.MOUNTAINS;
            } else {
                tripType = Types.CITY_BREAK;
            }
            if (valid) {
                TripRoomDatabase.databaseThread.execute(() -> {
                    Trip trip1 = new Trip(trip.getId(), title, location, tripType, (float)price, startDate, endDate, (int) rating);
                    tripViewModel.update(trip1);
                });
                this.finish();
            } else {
                Toast.makeText(getApplication(), "The data is not valid", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void endDatePicker() {
        editTextEndDate.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            editTextEndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, year, month, day);
            datePickerDialog.show();
        });
    }

    private void startDatePicker() {
        editTextStartDate.setOnClickListener(view -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view1, year1, monthOfYear, dayOfMonth) -> editTextStartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            datePickerDialog.show();
        });
    }

    private void setupSeekBar() {
        SeekBar.OnSeekBarChangeListener l = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                priceTextView.setText("Price: " + seekBarPrice.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        seekBarPrice.setOnSeekBarChangeListener(l);
    }
}