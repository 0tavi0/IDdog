package com.otavio.iddog.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.otavio.iddog.Adapters.AdapterURLs;
import com.otavio.iddog.Interfaces.ServiceAPI;
import com.otavio.iddog.Models.Dogs;
import com.otavio.iddog.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Categoria_labrador_Fragment extends Fragment {

    RecyclerView recyclerView;
    AdapterURLs recyclerAdapterURLS;
    List<Dogs> listDogs;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.labrador_fragment, container, false);

        SharedPreferences prefs = this.getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = prefs.getString("token", "null");


        listDogs = new ArrayList<>();

        Carregar(token,"labrador");

        recyclerView = view.findViewById(R.id.recycler_labrador);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);



        return view;

    }

    public void Carregar(String token, String categoria){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-iddog.idwall.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceAPI serviceAPI = retrofit.create(ServiceAPI.class);
        Call<Dogs> call = serviceAPI.getCategoria(token, categoria);

        call.enqueue(new Callback<Dogs>() {
            @Override
            public void onResponse(Call<Dogs> call, Response<Dogs> response) {

                Dogs dogs = response.body();
                dogs.setList(response.body().getList());

                listDogs.add(dogs);
                recyclerAdapterURLS = new AdapterURLs(dogs.getList(),getContext());
                recyclerView.setAdapter(recyclerAdapterURLS);


            }

            @Override
            public void onFailure(Call<Dogs> call, Throwable t) {

            }
        });
    }
}
