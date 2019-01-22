package com.smola.hiber.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class RouteSQL {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @JsonBackReference("a")
    @ManyToMany(mappedBy = "routesTravelled")
    private List<UserSQL> usersTravelled = new ArrayList<>();

    @JsonBackReference("b")
    @ManyToMany(mappedBy = "routesCreated")
    private List<UserSQL> usersCreated = new ArrayList<>();


    @NaturalId
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CoordinatesSQL> coordinates = new ArrayList<>();

    public RouteSQL() {
    }

    @JsonCreator
    public RouteSQL(@JsonProperty(value = "routeName") String name,
                    @JsonProperty(value = "coordinates") List<CoordinatesSQL> coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public RouteSQL(String name) {
        this.name = name;
    }


    public void addCoordinates(CoordinatesSQL coordinatesSQL) {
        coordinatesSQL.setRouteSQL(this);
        this.coordinates.add(coordinatesSQL);
    }

    public void removeCoordinates(CoordinatesSQL coordinatesSQL) {
        coordinatesSQL.setRouteSQL(null);
        this.coordinates.remove(coordinatesSQL);
    }

    public void addCoordinates(Collection<CoordinatesSQL> coordinates) {
        coordinates.forEach(this::addCoordinates);
    }

    public void removeCoordinates(Collection<CoordinatesSQL> coordinates) {
        coordinates.forEach(this::removeCoordinates);
    }


    public List<UserSQL> getUsersTravelled() {
        return usersTravelled;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<CoordinatesSQL> getCoordinates() {
        return coordinates;
    }

    public List<UserSQL> getUsersCreated() {
        return usersCreated;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsersTravelled(List<UserSQL> usersTravelled) {
        this.usersTravelled = usersTravelled;
    }

    public void setUsersCreated(List<UserSQL> usersCreated) {
        this.usersCreated = usersCreated;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(List<CoordinatesSQL> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteSQL routeSQL = (RouteSQL) o;
        return Objects.equals(name, routeSQL.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
