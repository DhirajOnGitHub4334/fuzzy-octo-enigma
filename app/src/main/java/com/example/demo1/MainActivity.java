package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.demo1.models.Photo;
import com.example.demo1.network.APIclient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
GridView gridView;
CustomAdapter customAdapter;
public static List<Photo> photoList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        photoList = new ArrayList<>();

        Call<List<Photo>> call= APIclient.apIinterface().getPhoto();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if(response.isSuccessful()){
                    photoList=response.body();

                    customAdapter = new CustomAdapter(response.body(),MainActivity.this);
                    gridView.setAdapter(customAdapter);

                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent= new Intent();
//                            intent.putExtra("Id",photoList.get(i).getId());
//                            intent.putExtra("URL",photoList.get(i).getUrl());
//                            intent.putExtra("ThumbnailUrl",photoList.get(i).getThumbnailUrl());
//                            intent.putExtra("name",photoList.get(i).getTitle());
                            startActivity(new Intent(getApplicationContext(),ItemDataActivity.class).putExtra("name",photoList.get(position).getId()).putExtra("URL",photoList.get(position).getUrl()).putExtra("ThumbnailUrl",photoList.get(position).getThumbnailUrl()).putExtra("name",photoList.get(position).getTitle()));
                        }
                    });
                }else
                {
                    Toast.makeText(getApplicationContext(), "error occured", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error occured"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CustomAdapter extends BaseAdapter
    {
        public List<Photo> photoList;
        public Context context;

        public CustomAdapter(List<Photo> photoList, Context context) {
            this.photoList = photoList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return photoList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_data,null);

            ImageView image = view.findViewById(R.id.imageView);

            Glide.with(context)
                    .load(photoList.get(position).getUrl())
                    .into(image);

            return view;
        }
    }
}