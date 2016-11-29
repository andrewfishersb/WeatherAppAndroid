package com.epicodus.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {
    public static final String TAG = WeatherActivity.class.getSimpleName();
    @Bind(R.id.forecastListView) ListView mListView;

    public ArrayList<Weather> mWeather = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        getWeather("Portland");
    }

    private void getWeather(String location){
        final WeatherService weatherService = new WeatherService();
        weatherService.getForecast(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mWeather = weatherService.processResults(response);

                WeatherActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] forecast = new String[mWeather.size()];
                        for (int i=0;i<forecast.length;i++){
                            forecast[i] = "Max Temp: " + toFahrenheit(mWeather.get(i).getmMax()) + " Min Temp: "+ toFahrenheit(mWeather.get(i).getmMin());
                        }
                        ArrayAdapter adapter = new ArrayAdapter(WeatherActivity.this,android.R.layout.simple_list_item_1, forecast);
                        mListView.setAdapter(adapter);
                    }
                });
            }
        });
    }

    public int toFahrenheit(double temp){
        double convert = temp * 1.8 +32;
        return (int) convert;
    }
}
