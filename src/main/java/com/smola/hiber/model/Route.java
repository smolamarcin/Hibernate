package com.smola.hiber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Route {
    @Id
    @JsonIgnore
    private String id;
    private String name;
    private List<Coordinates> coordinates = new ArrayList<>();
    private double rating;

    public Route() {
    }

    public Route(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(name, route.name) &&
                Objects.equals(coordinates, route.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates);
    }

    public void addCoordinates(List<Coordinates> coordinates) {
        this.coordinates.addAll(coordinates);
    }


    @Override
    public String toString() {
        return "Route{" +
                "name='" + name + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }

    public void setRate(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return this.rating;
    }

    public String getId() {
        return this.id;
    }
}
