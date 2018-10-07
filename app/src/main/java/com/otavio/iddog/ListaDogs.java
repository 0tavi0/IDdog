package com.otavio.iddog;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.otavio.iddog.Adapters.AdapterURLs;
import com.otavio.iddog.Interfaces.ServiceAPI;
import com.otavio.iddog.Models.Dogs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaDogs extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterURLs recyclerAdapterURLS;
    List<Dogs> listDogs;

    TextView txt_categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dogs);

        SharedPreferences prefs = getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = prefs.getString("token", "sem token");

        listDogs = new ArrayList<>();

        txt_categoria = findViewById(R.id.txt_categoria_nome);

        recyclerView = findViewById(R.id.recycler);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




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

                    txt_categoria.setText(response.body().getCategory());

                    Dogs dogs = response.body();
                    dogs.setList(response.body().getList());

                    Log.e("dogs",""+dogs.getList());

                    listDogs.add(dogs);


                   // recyclerAdapter.setListaDog(listDogs);

                    recyclerAdapterURLS = new AdapterURLs(dogs.getList(),getApplicationContext());
                    recyclerView.setAdapter(recyclerAdapterURLS);


                }
            }

            @Override
            public void onFailure(Call<Dogs> call, Throwable t) {
                Log.e("erro",""+t.getMessage());

            }
        });

    }
}
