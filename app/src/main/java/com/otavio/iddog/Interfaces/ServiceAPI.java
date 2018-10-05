package com.otavio.iddog.Interfaces;

import com.otavio.iddog.Login.User;
import com.otavio.iddog.Login.UserResponse;
import com.otavio.iddog.Models.Dogs;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiceAPI {

    //https://api-iddog.idwall.co/signup

    @POST("/signup")
    Call<UserResponse> login(@Body User user );

    @GET("feed")
    Call<Dogs> getDogs(@Header("Authorization") String authorization);


}
