package com.example.backposturecorrector.login;


import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;

public interface LoginApi {

    @POST("login")
    Call<LoginResponse> login(@Body LoginResponse loginResponse);
}
