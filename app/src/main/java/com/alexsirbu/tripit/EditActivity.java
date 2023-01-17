package com.alexsirbu.tripit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    private TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        testTextView = findViewById(R.id.textViewTest);
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");
    }
}