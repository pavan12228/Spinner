package com.example.ravi.spinner;

import com.google.gson.JsonArray;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Ravi on 22-12-2016.
 */

public interface Apiservice
{
    @GET("/movies.json")
    public void mymeth(Callback<JsonArray> callback);



}
