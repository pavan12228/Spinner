package com.example.ravi.spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    List<String> integerList;

    private static final String root_url = "http://api.androidhive.info/json";
    Spinner getSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSpin = (Spinner) findViewById(R.id.spinnerlist);
        integerList  = new ArrayList<>();
        inserUser();
        getSpin.setOnItemSelectedListener(this);
    }


    private void inserUser() {


        final RestAdapter adapter = new RestAdapter.Builder().setEndpoint(root_url).build();

        Apiservice api = adapter.create(Apiservice.class);
              api.mymeth(new Callback<JsonArray>() {
                  @Override
                  public void success(JsonArray jsonElements, Response response) {
                          try{

                              for (int i = 0; i < jsonElements.size(); i++) {

                                       JsonObject jsonObject= jsonElements.get(i).getAsJsonObject();
                                          int year =  jsonObject.get("releaseYear").getAsInt();
                                                  integerList.add(String.valueOf(year));






                              }



                          }catch (JsonIOException  e){

                              e.printStackTrace();
                          }





                  }

                  @Override
                  public void failure(RetrofitError error) {
                      Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                  }
              });
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,integerList);
         getSpin.setAdapter(stringArrayAdapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}