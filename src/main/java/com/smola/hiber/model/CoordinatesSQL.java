package com.smola.hiber.model;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CoordinatesSQL {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String latitude;
    private String longtitude;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private RouteSQL routeSQL;

    public CoordinatesSQL() {
    }

    public CoordinatesSQL(String latitude, String longtitude) {
        this.latitude = latitude;
        this.longtitude = longtitude;
    }


    public void setRouteSQL(RouteSQL routeSQL) {
        this.routeSQL = routeSQL;
    }

    public Long getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public RouteSQL getRouteSQL() {
        return routeSQL;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinatesSQL that = (CoordinatesSQL) o;
        return Objects.equals(latitude, that.latitude) &&
                Objects.equals(longtitude, that.longtitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longtitude);
    }
}
