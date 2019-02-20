package com.smola.hiber.services;


import com.smola.hiber.model.Route;
import com.smola.hiber.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface UserService {
    Page<User> retrieveAllUser(Pageable pageable);

    User findUserById(String id);

    Collection<Route> findRoutesCreatedByUser(String id);

    Collection<Route> findRoutesTravelledByUser(String id);

    User updateUserRoutes(String id, Route route, boolean isTravelled);

    User createUser(User user);

    boolean isUserExists(User user);

    Collection<User> retrieveUsersTravelled(String routeName);

    Page<User> findAll(Pageable pageable);

    User findUserByEmail(String email);

    User findByUsername(String name);
}
