package com.smola.hiber.model;


import javax.persistence.Embeddable;

@Embeddable
public class Coordinates {
    private final String latitude;
    private final String longtitude;


    public Coordinates() {
        this.longtitude = null;
        this.latitude = null;
    }

    public Coordinates(String latitude, String longtitude) {
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }
}
