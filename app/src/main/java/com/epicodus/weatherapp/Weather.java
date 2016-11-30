package com.epicodus.weatherapp;

import java.util.ArrayList;

/**
 * Created by Guest on 11/29/16.
 */
public class Weather {
    private int mMax;
    private int mMin;

    public Weather(int max, int min) {
        this.mMax = max;
        this.mMin = min;
    }

    public int getmMin() {

        return mMin;
    }


    public int getmMax() {

        return mMax;
    }
}
