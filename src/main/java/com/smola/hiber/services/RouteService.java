package com.smola.hiber.services;

import com.smola.hiber.model.Route;

public interface RouteService {
    Iterable<Route> retrieveAllRoutes();

    Route findById(String routeID);

    Route update(Route route);
}
