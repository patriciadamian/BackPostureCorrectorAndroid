package com.example.backposturecorrector.user.profile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface UserProfileApi {

    @PUT("users/")
    Call<UserProfileResponse> userProfile(@Header("Authorization") String auth, @Body UserProfileResponse userProfileResponse);
}
