package com.example.backposturecorrector.calibration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.backposturecorrector.R;
import com.example.backposturecorrector.Session;
import com.example.backposturecorrector.bluetooth.BluetoothConnector;
import com.example.backposturecorrector.client.ApiClient;
import com.example.backposturecorrector.statistics.AlertResponse;
import com.example.backposturecorrector.statistics.AnnualStatisticsActivity;
import com.example.backposturecorrector.statistics.StatisticsApi;
import com.example.backposturecorrector.statistics.WeeklyStatisticsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndCalibrationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button weeklyButton, annualButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration_end);

        BluetoothConnector.sendMessage("LOOP");

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    String message = BluetoothConnector.receiveMessage();
                    if (message != null && message.contains("ALERT")) {
                        message = "";
                        Long id = Session.getId();

                        StatisticsApi statisticsApi = ApiClient.getClient().create(StatisticsApi.class);
                        Call<AlertResponse> call = statisticsApi.saveAlert(Session.TOKEN, id);

                        call.enqueue(new Callback<AlertResponse>() {
                            @Override
                            public void onResponse(Call<AlertResponse> call, Response<AlertResponse> response) {
                                if (response.isSuccessful()) {
                                    Log.i("alert message success", response.body().toString());
                                } else {
                                    Log.e("alert message failed", response.errorBody().toString());
                                }
                            }

                            @Override
                            public void onFailure(Call<AlertResponse> call, Throwable t) {
                                Log.e("alert message failed", t.getLocalizedMessage());
                            }
                        });
                    }
                }
            }
        }).start();

        weeklyButton = findViewById(R.id.buttonHome);
        weeklyButton.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), WeeklyStatisticsActivity.class)));

        annualButton = findViewById(R.id.buttonAnnualStatistics);
        annualButton.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), AnnualStatisticsActivity.class)));
    }

    @Override
    public void onClick(View view) {

    }
}
