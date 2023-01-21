package com.alexsirbu.tripit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
import com.alexsirbu.tripit.domain.TripRoomDatabase;
import com.alexsirbu.tripit.domain.Types;
import com.alexsirbu.tripit.models.TripViewModel;

import java.util.Calendar;
import java.util.Objects;

public class CreateActivity extends AppCompatActivity {

    private TripViewModel tripViewModel;

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
        setContentView(R.layout.activity_create);

        tripViewModel = new ViewModelProvider(this).get(TripViewModel.class);

        setupViews();
    }

    private void setupViews() {
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextLocation = findViewById(R.id.editTextLocation);

        radioGroupType = findViewById(R.id.radioGroupType);

        priceTextView = findViewById(R.id.textViewPrice2);
        seekBarPrice = findViewById(R.id.seekBarPrice);

        setupSeekBar();

        editTextStartDate = findViewById(R.id.editTextStartDate);
        editTextEndDate = findViewById(R.id.editTextEndDate);

        ratingBar = findViewById(R.id.ratingBarTripRating);

        saveBtn = findViewById(R.id.buttonSave);
        cancelBtn = findViewById(R.id.buttonCancel);
        deleteBtn = findViewById(R.id.buttonDelete);

        setupButtons();
    }

    private void setupButtons() {
        startDatePicker();
        endDatePicker();
        cancelDeleteButton();
        saveButtonHandle();
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

            Types tripType = null;

            if (button == null) {
                valid = false;
            } else if (button.getText().equals("Sea side")) {
                tripType = Types.SEA_SIDE;
            } else if (button.getText().equals("Mountains")) {
                tripType = Types.MOUNTAINS;
            } else {
                tripType = Types.CITY_BREAK;
            }
            if (valid) {
                Types finalTripType = tripType;
                TripRoomDatabase.databaseThread.execute(() -> {
                    Trip trip = new Trip(tripViewModel.getLowestFreeId() + 1, title, location, finalTripType, (float)price, startDate, endDate, (int) rating);
                    tripViewModel.insert(trip);
                });
                this.finish();
            } else {
                Toast.makeText(getApplication(), "The data is not valid", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void cancelDeleteButton() {
        cancelBtn.setOnClickListener(view -> {
                this.finish();
        });
        deleteBtn.setOnClickListener(view -> {
            editTextTitle.setText("");
            editTextLocation.setText("");
            radioGroupType.clearCheck();
            seekBarPrice.setProgress(0);
            editTextStartDate.setText("");
            editTextEndDate.setText("");
            ratingBar.setRating(0);
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
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            editTextStartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, year, month, day);
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