package com.smola.hiber.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Document(collection = "user")
public class User {
    @Id
//    @JsonIgnore
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @DBRef
    private Set<Role> roles;
    private boolean enabled;
    private Set<Route> routesTravelled = new HashSet<>();
    private Set<Route> routesCreated = new HashSet<>();
    private Set<Route> ratedRoutes = new HashSet<>();

    public User() {
    }

    public User(String firsName) {
        this.firstName = firsName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public Set<Route> getRoutesTravelled() {
        return routesTravelled;
    }

    public Set<Route> getRoutesCreated() {
        return routesCreated;
    }
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<Route> getRatedRoutes() {
        return this.ratedRoutes;
    }



}
