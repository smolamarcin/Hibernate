package com.smola.hiber.controllers;

import com.smola.hiber.model.RouteSQL;
import com.smola.hiber.model.UserSQL;
import com.smola.hiber.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Iterable<UserSQL> retrieveAllUsers() {
        return this.userService.retrieveAllUser();
    }

    @GetMapping("/users/{userId}")
    public UserSQL retrieveUserById(@PathVariable Long userId){
        return this.userService.findUserById(userId);
    }

    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserSQL createUser(@RequestBody UserSQL userSQL){
        return this.userService.createUser(userSQL);
    }


    @GetMapping("/users/{userId}/routes/created/")
    Collection<RouteSQL> findRoutesCreatedByUser(@PathVariable Long userId) {
        return userService.findRoutesCreatedByUser(userId);
    }

    @GetMapping("/users/{userId}/routes/travelled/")
    Collection<RouteSQL> findRoutesTravelledByUser(@PathVariable Long userId) {
        return userService.findRoutesTravelledByUser(userId);
    }

    @PutMapping("/users/{userId}/routes")
    RouteSQL updateUserRoutes(@PathVariable Long userId,
                              @RequestParam(value = "travelled", required = false) boolean isTravelled,
                              @RequestBody RouteSQL routeSQL) {
        return userService.updateUserRoutes(userId, routeSQL, isTravelled);
    }

}
