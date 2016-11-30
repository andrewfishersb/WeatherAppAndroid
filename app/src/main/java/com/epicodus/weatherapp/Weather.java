package com.epicodus.weatherapp;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Guest on 11/29/16.
 */
public class Weather {
    int mMax;
    int mMin;
    String icon;
    long timestamp;

    public Weather(int max, int min, String icon, long timestamp) {
        this.mMax = max;
        this.mMin = min;
        this.icon = "http://openweathermap.org/img/w/" + icon+ ".png";
        this.timestamp = timestamp;
    }



    public String getIcon(){
        return icon;
    }

    public int getmMin() {

        return mMin;
    }

    public int getmMax() {
        return mMax;
    }

    public String getTimeStamp() {
        Date date = new Date(this.timestamp);
        return date.toString();
    }
}
