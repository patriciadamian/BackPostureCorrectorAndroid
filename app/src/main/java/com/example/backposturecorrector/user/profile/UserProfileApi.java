package com.example.backposturecorrector.user.profile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserProfileApi {

    @PUT("users/")
    Call<UserProfileResponse> userProfile(@Header("Authorization") String auth, @Body UserProfileResponse userProfileResponse);

    @GET("users/{id}")
    Call<UserProfileResponse> userById(@Header("Authorization") String auth, @Path("id") Long id);
}
