package com.example.backposturecorrector.statistics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface StatisticsApi {

    @GET("statistics/week/{id}")
    Call<List<WeeklyStatisticsResponse>> getWeeklyStatistics(@Header("Authorization") String auth, @Path("id") long id);

    @GET("statistics/year/{id}")
    Call<List<AnnualStatisticsResponse>> getAnnualStatistics(@Header("Authorization") String auth, @Path("id") long id);
}
