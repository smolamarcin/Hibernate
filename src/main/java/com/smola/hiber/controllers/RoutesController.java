package com.smola.hiber.controllers;

import com.smola.hiber.model.Route;
import com.smola.hiber.model.User;
import com.smola.hiber.services.RouteService;
import com.smola.hiber.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RoutesController {
    private RouteService routeService;
    private UserService userService;

    @Autowired
    public RoutesController(RouteService routeService, UserService userService) {
        this.routeService = routeService;
        this.userService = userService;
    }

    @GetMapping("routes/created/{id}")
    Collection<Route> findRoutesCreatedByUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return user.getRoutesCreated();
    }

    @GetMapping("routes/travelled/{id}")
    Collection<Route> findRoutesTravelledByUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return user.getRoutesTravelled();
    }
}
