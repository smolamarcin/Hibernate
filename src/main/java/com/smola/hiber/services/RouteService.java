package com.smola.hiber.services;

import com.smola.hiber.model.Route;
import com.smola.hiber.model.User;

import java.util.Collection;

public interface RouteService {

    Iterable<Route> retrieveAllRoutes();

}
