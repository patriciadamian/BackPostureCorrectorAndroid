package com.example.backposturecorrector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.backposturecorrector.statistics.AnnualStatisticsActivity;
import com.example.backposturecorrector.statistics.WeeklyStatisticsActivity;

public class HomeActivity extends AppCompatActivity {

    private Button weeklyButton, annualButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        weeklyButton = findViewById(R.id.buttonHome);
        weeklyButton.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), WeeklyStatisticsActivity.class)));

        annualButton = findViewById(R.id.buttonAnnualStatistics);
        annualButton.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), AnnualStatisticsActivity.class)));
    }
}
