package com.example.backposturecorrector.calibration;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.backposturecorrector.R;
import com.example.backposturecorrector.Session;
import com.example.backposturecorrector.bluetooth.BluetoothConnector;
import com.example.backposturecorrector.client.ApiClient;
import com.example.backposturecorrector.login.LoginErrorActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BothHandsCalibrationActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar progressBar;
    private Button doneButton;
    private Handler mHandler;
    private int progressInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration_both);

        doneButton = findViewById(R.id.buttonDone);
        doneButton.setOnClickListener(v -> {

            BluetoothConnector.sendMessage("DONE");
            String message = BluetoothConnector.receiveMessage();

            String[] split = message.replaceAll("\r", "").split("\n");

            String accelerometerX = split[0];
            String accelerometerY = split[1];
            String accelerometerZ = split[2];

            String gyroscopeX = split[3];
            String gyroscopeY = split[4];
            String gyroscopeZ = split[5];

            String magnetometerX = split[6];
            String magnetometerY = split[7];
            String magnetometerZ = split[8];

            Long id = Session.getId();

            CalibrationResponse calibrationResponse = new CalibrationResponse(id, accelerometerX, accelerometerY, accelerometerZ, gyroscopeX, gyroscopeY, gyroscopeZ, magnetometerX, magnetometerY, magnetometerZ);
            CalibrationApi calibrationApi = ApiClient.getClient().create(CalibrationApi.class);
            Call<CalibrationResponse> call = calibrationApi.calibrate(Session.TOKEN, calibrationResponse);

            call.enqueue(new Callback<CalibrationResponse>() {
                @Override
                public void onResponse(Call<CalibrationResponse> call, Response<CalibrationResponse> response) {
                    if (response.isSuccessful()) {
                        CharSequence text = "Calibration data saved!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                        toast.show();

                        startActivity(new Intent(getApplicationContext(), EndCalibrationActivity.class));
                    }
                }

                @Override
                public void onFailure(Call<CalibrationResponse> call, Throwable t) {
                    startActivity(new Intent(getApplicationContext(), LoginErrorActivity.class));
                }
            });

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
