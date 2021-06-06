package com.example.backposturecorrector.calibration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.backposturecorrector.R;


public class StartCalibrationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration_start);

        startButton = findViewById(R.id.buttonStart);
        startButton.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), StraightBackCalibrationActivity.class)));

    }

    @Override
    public void onClick(View view) {

    }
}
