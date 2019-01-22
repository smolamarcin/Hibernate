package com.smola.hiber.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    @Id
//    @JsonIgnore
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<Route> routesTravelled = new HashSet<>();
    private Set<Route> routesCreated = new HashSet<>();

    public User() {
    }

    public User(String firsName) {
        this.firstName = firsName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Route> getRoutesTravelled() {
        return routesTravelled;
    }

    public void setRoutesTravelled(Set<Route> routesTravelled) {
        this.routesTravelled = routesTravelled;
    }

    public Set<Route> getRoutesCreated() {
        return routesCreated;
    }

    public void setRoutesCreated(Set<Route> routesCreated) {
        this.routesCreated = routesCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    public boolean addTravelledRoute(Route route) {
        return this.routesTravelled.add(route);
    }

    public boolean addCreatedRoute(Route route) {
        return this.routesCreated.add(route);
    }
}
