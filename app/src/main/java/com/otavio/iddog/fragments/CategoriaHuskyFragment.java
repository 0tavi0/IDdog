package com.otavio.iddog.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.otavio.iddog.adapters.AdapterURLs;
import com.otavio.iddog.utils.ServiceAPI;
import com.otavio.iddog.models.Dogs;
import com.otavio.iddog.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriaHuskyFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterURLs recyclerAdapterURLS;
    List<Dogs> listDogs;
    private LinearLayoutManager linearLayoutManager;
    AlertDialog alerta;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.husky_fragment, container, false);

        SharedPreferences prefs = this.getActivity().getSharedPreferences(getString(R.string.token), Context.MODE_PRIVATE);
        String token = prefs.getString(getString(R.string.token), "null");

        listDogs = new ArrayList<>();

        Carregar(token);

        recyclerView = view.findViewById(R.id.recycler_husk);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);



        return view;

    }

    public void Carregar(String token){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-iddog.idwall.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceAPI serviceAPI = retrofit.create(ServiceAPI.class);
        Call<Dogs> call = serviceAPI.getDogs(token);

        call.enqueue(new Callback<Dogs>() {
            @Override
            public void onResponse(Call<Dogs> call, Response<Dogs> response) {

                if (response.isSuccessful()){


                    Dogs dogs = response.body();
                    dogs.setList(response.body().getList());

                    listDogs.add(dogs);
                    recyclerAdapterURLS = new AdapterURLs(dogs.getList(),getContext());
                    recyclerView.setAdapter(recyclerAdapterURLS);

                }else {
                    alertaError(response.message());
                }
            }

            @Override
            public void onFailure(Call<Dogs> call, Throwable t) {
                alertaError(t.getMessage().toString());

            }
        });
    }

    private void  alertaError(String mensagem){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.titleAlertDialog);
        builder.setMessage(mensagem);
        alerta = builder.create();
        alerta.show();

    }




}
