package com.example.backposturecorrector;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.backposturecorrector.bluetooth.BluetoothConnector;
import com.example.backposturecorrector.client.ApiClient;
import com.example.backposturecorrector.statistics.AlertResponse;
import com.example.backposturecorrector.statistics.AnnualStatisticsActivity;
import com.example.backposturecorrector.statistics.StatisticsApi;
import com.example.backposturecorrector.statistics.WeeklyStatisticsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private Button weeklyButton, annualButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        new Thread(new Runnable() {
//            public void run() {
//                String message = BluetoothConnector.receiveMessage();
//                if (message != null && message.contains("ALERT")) {
//                    Long id = Session.getId();
//
//                    StatisticsApi statisticsApi = ApiClient.getClient().create(StatisticsApi.class);
//                    Call<AlertResponse> call = statisticsApi.saveAlert(Session.TOKEN, id);
//
//                    call.enqueue(new Callback<AlertResponse>() {
//                        @Override
//                        public void onResponse(Call<AlertResponse> call, Response<AlertResponse> response) {
//                        }
//
//                        @Override
//                        public void onFailure(Call<AlertResponse> call, Throwable t) {
//                        }
//                    });
//                }
//            }
//        }).start();

        weeklyButton = findViewById(R.id.buttonHome);
        weeklyButton.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), WeeklyStatisticsActivity.class)));

        annualButton = findViewById(R.id.buttonAnnualStatistics);
        annualButton.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), AnnualStatisticsActivity.class)));
    }
}
