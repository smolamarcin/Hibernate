package com.smola.hiber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @JsonManagedReference
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(name = "users_travelled_routes", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "route_id", referencedColumnName = "id"))
    private List<Route> routesTravelled = new ArrayList<>();

    @JsonManagedReference
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(name = "users_created_routes", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "route_id", referencedColumnName = "id"))
    private List<Route> routesCreated = new ArrayList<>();

    public User(String firstName) {
        this.firstName = firstName;
    }


    public void addTravelledRoute(Route route) {
        this.routesTravelled.add(route);
        route.getUsersTravelled().add(this);
    }

    public void removeTravelledRoute(Route route) {
        this.routesTravelled.remove(route);
        route.getUsersTravelled().remove(this);
    }

    public void addCreatedRoute(Route route) {
        this.routesCreated.add(route);
         route.getUsersCreated().add(this);
    }

    public void removeCreatedRoute(Route route) {
        this.routesCreated.remove(route);
        route.getUsersCreated().remove(this);
    }

    public User() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Route> getRoutesCreated() {
        return routesCreated;
    }

    public List<Route> getRoutesTravelled() {
        return routesTravelled;
    }
}
