package com.smola.hiber.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Route {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @JsonBackReference
    @ManyToMany(mappedBy = "routesTravelled")
    private List<User> usersTravelled = new ArrayList<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "routesCreated")
    private List<User> usersCreated = new ArrayList<>();


    @NaturalId
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Coordinates> coordinates = new ArrayList<>();

    public Route() {
    }

    @JsonCreator
    public Route(@JsonProperty(value = "routeName") String name,
                 @JsonProperty(value = "coordinates") List<Coordinates> coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public Route(String name) {
        this.name = name;
    }


    public void addCoordinates(Coordinates coordinates) {
        coordinates.setRoute(this);
        this.coordinates.add(coordinates);
    }

    public void removeCoordinates(Coordinates coordinates) {
        coordinates.setRoute(null);
        this.coordinates.remove(coordinates);
    }

    public void addCoordinates(Collection<Coordinates> coordinates) {
        coordinates.forEach(this::addCoordinates);
    }

    public void removeCoordinates(Collection<Coordinates> coordinates) {
        coordinates.forEach(this::removeCoordinates);
    }


    public List<User> getUsersTravelled() {
        return usersTravelled;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Coordinates> getCoordinates() {
        return coordinates;
    }

    public List<User> getUsersCreated() {
        return usersCreated;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(name, route.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
