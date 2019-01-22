package com.smola.hiber.services;

import com.smola.hiber.model.RouteSQL;

public interface RouteService {

    Iterable<RouteSQL> retrieveAllRoutes();

}
