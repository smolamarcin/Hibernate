package com.smola.hiber.controllers;

import com.smola.hiber.exception.UserNotFoundException;
import com.smola.hiber.model.Route;
import com.smola.hiber.model.User;
import com.smola.hiber.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Iterable<User> retrieveAllUsers() {
        return this.userService.retrieveAllUser();
    }

    @GetMapping("/users/{userId}")
    public User retrieveUserById(@PathVariable Long userId){
        return this.userService.findUserById(userId);
    }

    @GetMapping("/users/{userId}/routes/created/")
    Collection<Route> findRoutesCreatedByUser(@PathVariable Long userId) {
        return userService.findRoutesCreatedByUser(userId);
    }

    @GetMapping("/users/{userId}/routes/travelled/")
    Collection<Route> findRoutesTravelledByUser(@PathVariable Long userId) {
        return userService.findRoutesTravelledByUser(userId);
    }

    @PutMapping("/users/{userId}/routes")
    Route updateUserRoutes(@PathVariable Long userId,
                           @RequestParam(value = "travelled", required = false) boolean isTravelled,
                           @RequestBody Route route) {
        return userService.updateUserRoutes(userId, route, isTravelled);
    }

}
