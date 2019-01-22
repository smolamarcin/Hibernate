package com.smola.hiber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Route {
    @Id
    @JsonIgnore
    private String id;
    private String name;
    private List<Coordinates> coordinates = new ArrayList<>();

    public Route() {
    }

    public Route(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Coordinates> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
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
}
