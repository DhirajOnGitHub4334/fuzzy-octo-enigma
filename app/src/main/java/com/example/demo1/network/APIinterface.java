package com.example.demo1.network;

import com.example.demo1.models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIinterface {
    @GET("/photos")
    Call<List<Photo>> getPhoto();
}
