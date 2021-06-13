package com.example.backposturecorrector.calibration;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.backposturecorrector.R;
import com.example.backposturecorrector.bluetooth.BluetoothConnector;

import static com.example.backposturecorrector.Session.IS_CALIBRATION_COMPLETE;

public class StraightBackCalibrationActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private Button nextButton;
    private Handler mHandler;
    private int progressInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration_straight);

        nextButton = findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(v -> {

            BluetoothConnector.sendMessage("RIGHT");

            IS_CALIBRATION_COMPLETE = true;
            startActivity(new Intent(getApplicationContext(), RightHandCalibrationActivity.class));
        });

        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        mHandler = new Handler();
        runnable.run();

    }

    Runnable runnable = () -> {
        try {
            updateProgress();
        } catch (Exception ignored) {

        }
    };

    private void updateProgress() {
        progressInt += 1;
        if (progressInt < 101) {
            progressBar.setProgress(progressInt);
            mHandler.postDelayed(runnable, progressInt);
        } else {
            mHandler.removeCallbacksAndMessages(null);
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void onClick(View view) {

    }
}
