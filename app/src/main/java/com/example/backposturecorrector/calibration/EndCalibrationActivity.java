package com.example.backposturecorrector.calibration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.backposturecorrector.R;
import com.example.backposturecorrector.statistics.AnnualStatisticsActivity;
import com.example.backposturecorrector.statistics.WeeklyStatisticsActivity;

public class EndCalibrationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button homeButton, annualButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration_end);

        homeButton = findViewById(R.id.buttonHome);
        homeButton.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), WeeklyStatisticsActivity.class)));

        annualButton = findViewById(R.id.buttonAnnualStatistics);
        annualButton.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), AnnualStatisticsActivity.class)));
    }

    @Override
    public void onClick(View view) {

    }
}
