package com.smola.hiber.controllers;

import com.smola.hiber.exception.ExceptionMessage;
import com.smola.hiber.model.Route;
import com.smola.hiber.services.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {

    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PutMapping("/users/{userId}/routes/{routeId}/rate")
    ResponseEntity<Route> rateRoute(@PathVariable String userId,
                                    @PathVariable String routeID,
                                    @RequestParam double rate) {
        if (this.ratingService.didUserRatedRoute(userId, routeID)) {
            throw new RuntimeException(ExceptionMessage.ALREADY_RATED_EXCEPTION_MESSAGE);
        } else {
            this.ratingService.rateRoute(userId, routeID, rate);
            return ResponseEntity.ok().build();
        }
    }
}
