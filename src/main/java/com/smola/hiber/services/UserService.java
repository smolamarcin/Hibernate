package com.smola.hiber.services;


import com.smola.hiber.model.Route;
import com.smola.hiber.model.User;

import java.util.Collection;

public interface UserService {
    Iterable<User> retrieveAllUser();

    User findUserById(String id);

    Collection<Route> findRoutesCreatedByUser(String id);

    Collection<Route> findRoutesTravelledByUser(String id);

    User updateUserRoutes(String id, Route route, boolean isTravelled);

    User createUser(User user);

    boolean isUserExists(User user);

    Collection<User> retrieveUsersTravelled(String routeName);

}
