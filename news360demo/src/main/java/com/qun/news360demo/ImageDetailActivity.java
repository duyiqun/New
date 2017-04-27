package com.qun.news360demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class ImageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        PhotoView imageView = (PhotoView) findViewById(R.id.iv);
        String url = getIntent().getStringExtra("url");
        Glide.with(ImageDetailActivity.this).load(url).into(imageView);
    }
}
