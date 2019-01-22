package com.smola.hiber.services;

import com.smola.hiber.model.RouteSQL;
import com.smola.hiber.model.UserSQL;

import java.util.Collection;

public interface UserService {
    Iterable<UserSQL> retrieveAllUser();

    UserSQL findUserById(Long id);

    Collection<RouteSQL> findRoutesCreatedByUser(Long id);

    Collection<RouteSQL> findRoutesTravelledByUser(Long id);

    RouteSQL updateUserRoutes(Long id, RouteSQL routeSQL, boolean isTravelled);

    UserSQL createUser(UserSQL userSQL);
}
