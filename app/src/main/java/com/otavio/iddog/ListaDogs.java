package com.otavio.iddog;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.otavio.iddog.Interfaces.ServiceAPI;
import com.otavio.iddog.Login.UserResponse;
import com.otavio.iddog.Models.Dogs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaDogs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dogs);

        SharedPreferences prefs = getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = prefs.getString("token", "sem token");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-iddog.idwall.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceAPI serviceAPI = retrofit.create(ServiceAPI.class);
        Call<Dogs> call = serviceAPI.getDogs(token);

        call.enqueue(new Callback<Dogs>() {
            @Override
            public void onResponse(Call<Dogs> call, Response<Dogs> response) {
                Log.e("Code",""+response.code());

                if (response.isSuccessful()){
                    Log.e("responser",""+response.body().getList());


                }
            }

            @Override
            public void onFailure(Call<Dogs> call, Throwable t) {
                Log.e("erro",""+t.getMessage());

            }
        });

    }
}
