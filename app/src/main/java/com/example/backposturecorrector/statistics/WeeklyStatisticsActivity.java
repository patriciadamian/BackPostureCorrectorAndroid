package com.example.backposturecorrector.statistics;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.backposturecorrector.R;
import com.example.backposturecorrector.Session;
import com.example.backposturecorrector.client.ApiClient;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

public class WeeklyStatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_statistics);

        long id = Session.getId();
        StatisticsApi statisticsApi = ApiClient.getClient().create(StatisticsApi.class);
        Call<List<WeeklyStatisticsResponse>> call = statisticsApi.getWeeklyStatistics(Session.TOKEN, id);

        call.enqueue(new Callback<List<WeeklyStatisticsResponse>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<WeeklyStatisticsResponse>> call, Response<List<WeeklyStatisticsResponse>> response) {
                if (response.isSuccessful()) {

                    List<WeeklyStatisticsResponse> weeklyStatisticsResponse = response.body();
                    AnyChartView anyChartView = findViewById(R.id.any_chart_view);
                    anyChartView.setProgressBar(findViewById(R.id.progress_bar));

                    Cartesian cartesian = AnyChart.column();

                    List<DataEntry> data = new ArrayList<>();
                    data.add(new ValueDataEntry("SUN", getNumberOfCorrectionAlerts(weeklyStatisticsResponse, SUNDAY)));
                    data.add(new ValueDataEntry("MON", getNumberOfCorrectionAlerts(weeklyStatisticsResponse, MONDAY)));
                    data.add(new ValueDataEntry("TUE", getNumberOfCorrectionAlerts(weeklyStatisticsResponse, TUESDAY)));
                    data.add(new ValueDataEntry("WED", getNumberOfCorrectionAlerts(weeklyStatisticsResponse, WEDNESDAY)));
                    data.add(new ValueDataEntry("THU", getNumberOfCorrectionAlerts(weeklyStatisticsResponse, THURSDAY)));
                    data.add(new ValueDataEntry("FRI", getNumberOfCorrectionAlerts(weeklyStatisticsResponse, FRIDAY)));
                    data.add(new ValueDataEntry("SAT", getNumberOfCorrectionAlerts(weeklyStatisticsResponse, SATURDAY)));

                    Column column = cartesian.column(data);

                    column.tooltip()
                            .titleFormat("{%X}")
                            .position(Position.CENTER_BOTTOM)
                            .anchor(Anchor.CENTER_BOTTOM)
                            .offsetX(0d)
                            .offsetY(5d)
                            .format("{%Value}{groupsSeparator: }");

                    cartesian.animation(true);
                    cartesian.title("Weekly correction alerts");

                    cartesian.yScale().minimum(0d);

                    cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

                    cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
                    cartesian.interactivity().hoverMode(HoverMode.BY_X);

                    cartesian.xAxis(0).title("Day of week");
                    cartesian.yAxis(0).title("Number of correction alerts");

                    anyChartView.setChart(cartesian);
                } else {
                    CharSequence text = "Something went wrong!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<WeeklyStatisticsResponse>> call, Throwable t) {
                CharSequence text = "Something went wrong!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        });
    }

    private int getNumberOfCorrectionAlerts(List<WeeklyStatisticsResponse> weeklyStatisticsResponse, DayOfWeek daysOfWeek) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return weeklyStatisticsResponse.stream()
                    .filter(w -> daysOfWeek.name().equalsIgnoreCase(w.getDayOfWeek()))
                    .findFirst()
                    .map(WeeklyStatisticsResponse::getNumberOfAlerts)
                    .orElse(0);
        } else {
            return 0;
        }
    }
}
