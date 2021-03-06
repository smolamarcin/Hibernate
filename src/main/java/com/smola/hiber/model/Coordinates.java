package com.smola.hiber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Coordinates {
    @Id
    @JsonIgnore
    private String id;
    private String latitude;
    private String longtitude;

    public Coordinates() {
    }

    public Coordinates(String latitude, String longtitude) {
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(latitude, that.latitude) &&
                Objects.equals(longtitude, that.longtitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longtitude);
    }
}
