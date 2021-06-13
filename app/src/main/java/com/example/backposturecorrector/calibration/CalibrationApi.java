package com.example.backposturecorrector.calibration;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CalibrationApi {

    @POST("calibrate")
    Call<CalibrationResponse> calibrate(@Header("Authorization") String auth, @Body CalibrationResponse loginResponse);
}
