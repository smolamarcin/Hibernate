package com.smola.hiber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserSQL {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @JsonIgnore
    @JsonManagedReference("a")
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(name = "users_travelled_routes", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "route_id", referencedColumnName = "id"))
    private List<RouteSQL> routesTravelled = new ArrayList<>();

    @JsonIgnore
    @JsonManagedReference("b")
    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(name = "users_created_routes", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "route_id", referencedColumnName = "id"))
    private List<RouteSQL> routesCreated = new ArrayList<>();

    public UserSQL(String firstName) {
        this.firstName = firstName;
    }


    public void addTravelledRoute(RouteSQL routeSQL) {
        this.routesTravelled.add(routeSQL);
        routeSQL.getUsersTravelled().add(this);
    }

    public void removeTravelledRoute(RouteSQL routeSQL) {
        this.routesTravelled.remove(routeSQL);
        routeSQL.getUsersTravelled().remove(this);
    }

    public void addCreatedRoute(RouteSQL routeSQL) {
        this.routesCreated.add(routeSQL);
         routeSQL.getUsersCreated().add(this);
    }

    public void removeCreatedRoute(RouteSQL routeSQL) {
        this.routesCreated.remove(routeSQL);
        routeSQL.getUsersCreated().remove(this);
    }

    public UserSQL() {
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

    public List<RouteSQL> getRoutesCreated() {
        return routesCreated;
    }

    public List<RouteSQL> getRoutesTravelled() {
        return routesTravelled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoutesTravelled(List<RouteSQL> routesTravelled) {
        this.routesTravelled = routesTravelled;
    }

    public void setRoutesCreated(List<RouteSQL> routesCreated) {
        this.routesCreated = routesCreated;
    }
}
