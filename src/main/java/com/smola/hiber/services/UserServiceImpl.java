package com.smola.hiber.services;

import com.smola.hiber.exception.UserNotFoundException;
import com.smola.hiber.model.Route;
import com.smola.hiber.model.User;
import com.smola.hiber.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> retrieveAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @Override
    public Collection<Route> findRoutesCreatedByUser(Long id) {
        User user = this.findUserById(id);
        return user.getRoutesCreated();
    }

    @Override
    public Collection<Route> findRoutesTravelledByUser(Long id) {
        User user = this.findUserById(id);
        return user.getRoutesTravelled();
    }

    @Override
    public Route updateUserRoutes(Long userId, Route route, boolean isTravelled) {
        User user = this.findUserById(userId);
        if (isTravelled) {
            user.addTravelledRoute(route);
            user.addCreatedRoute(route);
        } else {
            user.addCreatedRoute(route);
        }
        return route;
    }
}
