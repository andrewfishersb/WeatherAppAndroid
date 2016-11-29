package com.epicodus.weatherapp;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Guest on 11/29/16.
 */
public class WeatherService {

    public static void getForecast(String zip, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WEATHER_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.WEATHER_ZIP_QUERY, zip);
        urlBuilder.addQueryParameter(Constants.WEATHER_API_QUERY_PARAMETER, Constants.WEATHER_KEY);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }
}
