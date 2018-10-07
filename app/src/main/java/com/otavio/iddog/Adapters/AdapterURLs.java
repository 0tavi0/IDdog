package com.otavio.iddog.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.otavio.iddog.DogDetails;
import com.otavio.iddog.Models.Dogs;
import com.otavio.iddog.R;

import java.util.List;

public class AdapterURLs extends RecyclerView.Adapter<AdapterURLs.ViewHolder> {
    List<String> listaURL;
    Context context;

    public AdapterURLs(List<String> listaURL, Context context) {
        this.listaURL = listaURL;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dog,parent,false);
        return new AdapterURLs.ViewHolder(view);     }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(context)
                .load(listaURL.get(position))
                .into(holder.ima);

        holder.ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, DogDetails.class);
                it.putExtra("url", listaURL.get(position));
                context.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaURL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ima;
        public ViewHolder(View itemView) {
            super(itemView);
            ima = itemView.findViewById(R.id.img_dog);
        }
    }
}
