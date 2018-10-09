package com.otavio.iddog.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.otavio.iddog.activitys.DogDetailsActivity;
import com.otavio.iddog.R;

import java.util.List;

public class AdapterURLs extends RecyclerView.Adapter<AdapterURLs.ViewHolder> {
    List<String> listaURL;
    Context context;
    ProgressBar progressBar;

    public AdapterURLs(List<String> listaURL, Context context) {
        this.listaURL = listaURL;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Fresco.initialize(context);

        View view = LayoutInflater.from(context).inflate(R.layout.item_dog,parent,false);
        return new AdapterURLs.ViewHolder(view);     }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Uri imageUri = Uri.parse(listaURL.get(position));
        holder.draweeView.setImageURI(imageUri);

        holder.ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context, DogDetailsActivity.class);
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
        ProgressBar progressBar;
        SimpleDraweeView draweeView;

        public ViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress);
            ima = itemView.findViewById(R.id.img_dog);
            draweeView = itemView.findViewById(R.id.img_dog);
        }
    }
}
