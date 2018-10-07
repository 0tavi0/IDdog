package com.otavio.iddog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DogDetails extends AppCompatActivity {

    ImageView image_details_dog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_details);

        image_details_dog = findViewById(R.id.img_details);

        //Pega a intent de outra activity
        Intent it = getIntent();

        //Recuperei a string da outra activity
        String url = it.getStringExtra("url");

        Log.i("Informacao: ", url);

        Glide.with(this)
                .load(url)
                .into(image_details_dog);


    }
}
