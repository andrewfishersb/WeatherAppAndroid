package com.epicodus.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 11/29/16.
 */
public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder> {
    private ArrayList<Weather> mWeather = new ArrayList<>();
    private Context mContext;

    public WeatherListAdapter(Context context, ArrayList<Weather> weather) {
        mContext = context;
        mWeather = weather;
    }

    @Override
    public WeatherListAdapter.WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherListAdapter.WeatherViewHolder holder, int position){
        holder.bindWeather(mWeather.get(position));
    }

    @Override
    public int getItemCount(){
        return mWeather.size();
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.maxText) TextView mMaxText;
        @Bind(R.id.minText) TextView mMinText;
        @Bind(R.id.weatherIcon) ImageView mWeatherIcon;
        @Bind(R.id.dateText) TextView mDateText;

        private Context mContext;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindWeather(Weather weather) {
            Picasso.with(mContext).load(weather.getIcon()).into(mWeatherIcon);
            mMaxText.setText("High: " + weather.getmMax());
            mMinText.setText("Low: " + weather.getmMin());
            mDateText.setText(weather.getTimeStamp());

        }
    }
}
