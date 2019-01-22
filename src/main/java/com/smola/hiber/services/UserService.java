package com.smola.hiber.services;

import com.smola.hiber.model.Route;
import com.smola.hiber.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    Iterable<User> retrieveAllUser();

    User findUserById(Long id);

    Collection<Route> findRoutesCreatedByUser(Long id);

    Collection<Route> findRoutesTravelledByUser(Long id);

    Route updateUserRoutes(Long id, Route route, boolean isTravelled);
}
