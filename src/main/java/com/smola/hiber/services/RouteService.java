package com.smola.hiber.services;

import com.smola.hiber.model.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RouteService {
    Page<Route> retrieveAllRoutes(Pageable pageable);

    Route findById(String routeID);

    Route update(Route route);
}
