package com.epicodus.weatherapp;

import java.util.ArrayList;

/**
 * Created by Guest on 11/29/16.
 */
public class Weather {
    private double mMax;
    private double mMin;

    public Weather(double max, double min) {
        this.mMax = max;
        this.mMin = min;
    }

    public double getmMin() {

        return mMin;
    }

    public double getmMax() {

        return mMax;
    }
}
