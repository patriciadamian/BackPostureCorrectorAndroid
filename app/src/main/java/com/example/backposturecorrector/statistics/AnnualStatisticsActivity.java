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
import com.anychart.charts.Polar;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.PolarSeriesType;
import com.anychart.enums.ScaleStackMode;
import com.anychart.enums.ScaleTypes;
import com.anychart.enums.TooltipDisplayMode;
import com.anychart.scales.Linear;
import com.example.backposturecorrector.R;
import com.example.backposturecorrector.Session;
import com.example.backposturecorrector.client.ApiClient;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AnnualStatisticsActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annual_statistics);

        long id = Session.getId();
        StatisticsApi statisticsApi = ApiClient.getClient().create(StatisticsApi.class);
        Call<List<AnnualStatisticsResponse>> call = statisticsApi.getAnnualStatistics(Session.TOKEN, id);

        call.enqueue(new Callback<List<AnnualStatisticsResponse>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<AnnualStatisticsResponse>> call, Response<List<AnnualStatisticsResponse>> response) {
                if (response.isSuccessful()) {

                    List<AnnualStatisticsResponse> annualStatisticsResponse = response.body();
                    AnyChartView anyChartView = findViewById(R.id.any_chart_view);
                    anyChartView.setProgressBar(findViewById(R.id.progress_bar));

                    Polar polar = AnyChart.polar();

                    List<DataEntry> data = new ArrayList<>();
                    data.add(new ValueDataEntry("JAN", getNumberOfCorrectionAlerts(annualStatisticsResponse, Month.JANUARY)));
                    data.add(new ValueDataEntry("FEB", getNumberOfCorrectionAlerts(annualStatisticsResponse, Month.FEBRUARY)));
                    data.add(new ValueDataEntry("MAR", getNumberOfCorrectionAlerts(annualStatisticsResponse, Month.MARCH)));
                    data.add(new ValueDataEntry("APR", getNumberOfCorrectionAlerts(annualStatisticsResponse, Month.APRIL)));
                    data.add(new ValueDataEntry("MAY", getNumberOfCorrectionAlerts(annualStatisticsResponse, Month.MAY)));
                    data.add(new ValueDataEntry("JUN", getNumberOfCorrectionAlerts(annualStatisticsResponse, Month.JUNE)));
                    data.add(new ValueDataEntry("JUL", getNumberOfCorrectionAlerts(annualStatisticsResponse, Month.JULY)));
                    data.add(new ValueDataEntry("AUG", getNumberOfCorrectionAlerts(annualStatisticsResponse, Month.AUGUST)));
                    data.add(new ValueDataEntry("SEP", getNumberOfCorrectionAlerts(annualStatisticsResponse, Month.SEPTEMBER)));
                    data.add(new ValueDataEntry("OCT", getNumberOfCorrectionAlerts(annualStatisticsResponse, Month.OCTOBER)));
                    data.add(new ValueDataEntry("NOV", getNumberOfCorrectionAlerts(annualStatisticsResponse, Month.NOVEMBER)));
                    data.add(new ValueDataEntry("DEC", getNumberOfCorrectionAlerts(annualStatisticsResponse, Month.DECEMBER)));

                    Set set = Set.instantiate();
                    set.data(data);
                    Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");

                    polar.column(series1Data);

                    polar.title("Number of correction alerts by Year");

                    polar.sortPointsByX(true)
                            .defaultSeriesType(PolarSeriesType.COLUMN)
                            .yAxis(false)
                            .xScale(ScaleTypes.ORDINAL);

                    polar.title().margin().bottom(20d);

                    ((Linear) polar.yScale(Linear.class)).stackMode(ScaleStackMode.VALUE);

                    polar.tooltip()
                            .displayMode(TooltipDisplayMode.UNION);

                    anyChartView.setChart(polar);
                } else {
                    CharSequence text = "Something went wrong!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<AnnualStatisticsResponse>> call, Throwable t) {
                CharSequence text = "Something went wrong!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        });
    }

    private int getNumberOfCorrectionAlerts(List<AnnualStatisticsResponse> annualStatisticsResponses, Month month) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return annualStatisticsResponses.stream()
                    .filter(m -> month.name().equalsIgnoreCase(m.getMonthOfYear()))
                    .findFirst()
                    .map(AnnualStatisticsResponse::getNumberOfAlerts)
                    .orElse(0);
        } else {
            return 0;
        }
    }

}
