package com.otavio.iddog.utils;

import com.otavio.iddog.models.User;
import com.otavio.iddog.models.UserResponse;
import com.otavio.iddog.models.Dogs;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceAPI {

    @POST("/signup")
    Call<UserResponse> login(@Body User user );

    @GET("feed")
    Call<Dogs> getDogs(@Header("Authorization") String authorization);

    @GET("feed")
    Call<Dogs> getCategoria(@Header("Authorization") String authorization, @Query("category") String dog);


}
