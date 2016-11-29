package com.epicodus.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;

public class WeatherActivity extends AppCompatActivity {
    public static final String TAG = WeatherActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getWeather("97201");
    }

    private void getWeather(String zip){
        final WeatherService weatherService = new WeatherService();
        weatherService.getForecast(zip, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
