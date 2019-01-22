package com.smola.hiber.services;

import com.smola.hiber.exception.UserNotFoundException;
import com.smola.hiber.model.RouteSQL;
import com.smola.hiber.model.UserSQL;
import com.smola.hiber.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<UserSQL> retrieveAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public UserSQL findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("UserSQL not found!"));
    }

    @Override
    public Collection<RouteSQL> findRoutesCreatedByUser(Long id) {
        UserSQL userSQL = this.findUserById(id);
        return userSQL.getRoutesCreated();
    }

    @Override
    public Collection<RouteSQL> findRoutesTravelledByUser(Long id) {
        UserSQL userSQL = this.findUserById(id);
        return userSQL.getRoutesTravelled();
    }

    @Override
    public RouteSQL updateUserRoutes(Long userId, RouteSQL routeSQL, boolean isTravelled) {
        UserSQL userSQL = this.findUserById(userId);
        if (isTravelled) {
            userSQL.addTravelledRoute(routeSQL);
            userSQL.addCreatedRoute(routeSQL);
        } else {
            userSQL.addCreatedRoute(routeSQL);
        }
        return routeSQL;
    }

    @Override
    public UserSQL createUser(UserSQL userSQL) {
        return this.userRepository.save(userSQL);
    }
}
