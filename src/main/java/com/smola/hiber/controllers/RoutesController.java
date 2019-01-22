package com.smola.hiber.controllers;

import com.smola.hiber.model.RouteSQL;
import com.smola.hiber.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoutesController {
    private RouteService routeService;

    @Autowired
    public RoutesController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping(value = "/routes")
    Iterable<RouteSQL> retrieveAllRoutes(){
        return this.routeService.retrieveAllRoutes();
    }
}
