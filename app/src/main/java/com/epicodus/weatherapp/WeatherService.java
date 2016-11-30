package com.epicodus.weatherapp;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 11/29/16.
 */
public class WeatherService {

    public static void getForecast(String zip, Callback callback){

        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WEATHER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.WEATHER_LOCATION_QUERY, zip);
        urlBuilder.addQueryParameter(Constants.WEATHER_API_QUERY_PARAMETER, Constants.WEATHER_KEY);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Weather> processResults(Response response) {
        ArrayList<Weather> weathers =  new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject weatherJSON = new JSONObject(jsonData);
                JSONArray listJSON = weatherJSON.getJSONArray("list");
                for(int i = 0; i < listJSON.length(); i ++){
                    JSONObject currentWeather = listJSON.getJSONObject(i);
                    int max = toFahrenheit(currentWeather.getJSONObject("temp").getDouble("max"));
                    int min = toFahrenheit(currentWeather.getJSONObject("temp").getDouble("min"));
                    JSONArray weatherListJSON = currentWeather.getJSONArray("weather");
                    String icon = weatherListJSON.getJSONObject(0).getString("icon");
                    Weather dayWeather = new Weather(max,min);
                    weathers.add(dayWeather);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weathers;


    }

    public int toFahrenheit(double temp){
        double convert = temp * 1.8 +32;
        return (int) convert;
    }
}
