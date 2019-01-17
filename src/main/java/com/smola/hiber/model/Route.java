package com.smola.hiber.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "Route")
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue
    private Long id;

//    private List<Coordinates> coordinates;

    @ManyToMany(mappedBy = "routes")
    private List<User> users = new ArrayList<>();

    @NaturalId
    private String name;

    public Route(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //    public Route(List<Coordinates> coordinates) {
//        this.coordinates = coordinates;
//    }



    public Route() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public List<Coordinates> getCoordinates() {
//        return coordinates;
//    }
//
//    public void setCoordinates(List<Coordinates> coordinates) {
//        this.coordinates = coordinates;
//    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
