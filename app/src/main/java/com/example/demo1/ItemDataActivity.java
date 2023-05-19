package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ItemDataActivity extends AppCompatActivity {
ImageView imageView;
TextView textId,textTitle,textUrl,textThumbnail;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_data);

        imageView = findViewById(R.id.imageView);
        textId = findViewById(R.id.textId);
        textTitle = findViewById(R.id.textTitle);
        textUrl = findViewById(R.id.textUrl);
        textThumbnail = findViewById(R.id.textThumbnail);

        Intent intent = getIntent();

        String id = intent.getStringExtra("Id");
        String name = intent.getStringExtra("name");
        String Url = intent.getStringExtra("Url");
        String Thumbnail = intent.getStringExtra("Thumbnail");

        textId.setText(id);
        textTitle.setText(name);
        textUrl.setText(Url);
        textThumbnail.setText(Thumbnail);

        Glide.with(this)
                .load(imageView)
                .into(imageView);

    }
}