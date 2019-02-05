package com.smola.hiber.controllers;

import com.smola.hiber.model.Route;
import com.smola.hiber.model.User;
import com.smola.hiber.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public Page<User> retrieveAllUsers(Pageable pageable) {
        return this.userService.retrieveAllUser(pageable);
    }


    @GetMapping("/users/{userId}")
    public User retrieveUserById(@PathVariable String userId) {
        return this.userService.findUserById(userId);
    }

    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
//        return ResponseEntity.created().body(this.userService.createUser(user));
        return new ResponseEntity(this.userService.createUser(user), HttpStatus.CREATED);
    }


    @GetMapping("/users/{userId}/routes/created/")
    Collection<Route> findRoutesCreatedByUser(@PathVariable String userId) {
        return userService.findRoutesCreatedByUser(userId);
    }

    @GetMapping("/users/{userId}/routes/travelled/")
    Collection<Route> findRoutesTravelledByUser(@PathVariable String userId) {
        return userService.findRoutesTravelledByUser(userId);
    }

    @PutMapping("/users/{userId}/routes")
    User updateUserRoutes(@PathVariable String userId,
                          @RequestParam(value = "travelled", required = false) boolean isTravelled,
                          @RequestBody Route route) {
        return userService.updateUserRoutes(userId, route, isTravelled);
    }

    @GetMapping("/users/routes/{routeName}")
    Collection<User> retrieveUsersTravelled(@PathVariable String routeName) {
        return userService.retrieveUsersTravelled(routeName);
    }


}
