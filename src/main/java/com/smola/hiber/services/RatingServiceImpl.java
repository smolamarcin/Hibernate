package com.smola.hiber.services;

import com.smola.hiber.model.Route;
import com.smola.hiber.model.User;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    private RouteService routeService;
    private UserService userService;

    public RatingServiceImpl(RouteService routeService, UserService userService) {
        this.routeService = routeService;
        this.userService = userService;
    }

    @Override
    public void rateRoute(String userId, String routeID, double rate) {
        Route route = this.routeService.findById(routeID);
        this.rateRoute(route, rate);
    }

    @Override
    public boolean didUserRatedRoute(String userId, String routeID) {
        User user = this.userService.findUserById(userId);
        Route route = this.routeService.findById(routeID);
        return user.getRatedRoutes().contains(route);
    }

    private void rateRoute(Route route, double rate) {
        double currentRating = route.getRating();
        route.setRate((currentRating + rate) / 2);
        this.routeService.update(route);
    }
}
