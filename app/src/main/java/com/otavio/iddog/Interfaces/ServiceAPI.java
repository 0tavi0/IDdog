package com.otavio.iddog.Interfaces;

import com.otavio.iddog.Login.User;
import com.otavio.iddog.Login.UserResponse;
import com.otavio.iddog.Models.Dogs;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceAPI {

    //https://api-iddog.idwall.co/signup

    @POST("/signup")
    Call<UserResponse> login(@Body User user );

    @GET("feed")
    Call<Dogs> getDogs(@Header("Authorization") String authorization);

    @GET("feed")
    Call<Dogs> getCategoria(@Header("Authorization") String authorization, @Query("category") String dog);


}
