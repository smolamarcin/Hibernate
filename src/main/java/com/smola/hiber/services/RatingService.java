package com.smola.hiber.services;

public interface RatingService {
    void rateRoute(String userId, String routeID, double rate);
    boolean didUserRatedRoute(String userId, String routeID);
}
