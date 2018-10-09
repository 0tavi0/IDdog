package com.otavio.iddog.activitys;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.otavio.iddog.R;

public class DogDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fresco.initialize(this);
        setContentView(R.layout.activity_dog_details);

        //pegando a url da imagem do adapetUrls
        Intent it = getIntent();
        String url = it.getStringExtra("url");

        Uri imageUri = Uri.parse(url);
        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.img_details);
        draweeView.setImageURI(imageUri);


    }
}
