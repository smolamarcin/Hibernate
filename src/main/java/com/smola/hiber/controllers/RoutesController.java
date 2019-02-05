package com.smola.hiber.controllers;

import com.smola.hiber.model.Route;
import com.smola.hiber.services.RouteService;
import com.smola.hiber.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoutesController {
    private RouteService routeService;
    private UserService userService;

    @Autowired
    public RoutesController(RouteService routeService, UserService userService) {
        this.routeService = routeService;
        this.userService = userService;
    }

    @GetMapping(value = "/routes")
    Page<Route> retrieveAllRoutes(Pageable pageable) {
        return this.routeService.retrieveAllRoutes(pageable);
    }


}
