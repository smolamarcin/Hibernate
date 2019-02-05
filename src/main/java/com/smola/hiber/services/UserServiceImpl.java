package com.smola.hiber.services;

import com.smola.hiber.exception.ExceptionMessage;
import com.smola.hiber.exception.UserAlreadyExistsException;
import com.smola.hiber.exception.ResourceNotFoundException;
import com.smola.hiber.model.Route;
import com.smola.hiber.model.User;
import com.smola.hiber.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> retrieveAllUser(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    @Override
    public Collection<Route> findRoutesCreatedByUser(String id) {
        User user = this.findUserById(id);
        return user.getRoutesCreated();
    }

    @Override
    public Collection<Route> findRoutesTravelledByUser(String id) {
        User user = this.findUserById(id);
        return user.getRoutesTravelled();
    }

    @Override
    public User updateUserRoutes(String userId, Route route, boolean isTravelled) {
        User user = this.findUserById(userId);
        if (isTravelled) {
            user.addTravelledRoute(route);
            user.addCreatedRoute(route);
            user = this.userRepository.save(user);
        } else {
            user.addCreatedRoute(route);
            user = this.userRepository.save(user);
        }
        return user;
    }

    @Override
    public User createUser(User user) {
        if (this.isUserExists(user)) {
            throw new UserAlreadyExistsException(ExceptionMessage.USER_EMAIL_ALREADY_EXISTS);
        }
        return this.userRepository.save(user);
    }

    @Override
    public boolean isUserExists(User user) {
        Optional<User> userRepositoryByEmail = this.userRepository.findByEmail(user.getEmail());
        return userRepositoryByEmail.isPresent();
    }

    @Override
    public Collection<User> retrieveUsersTravelled(String routeName) {
        List<User> globetrotters = new ArrayList<>();
        this.userRepository.findAll().forEach(user -> {
            user.getRoutesTravelled().forEach(e -> {
                if (e.getName().equalsIgnoreCase(routeName)) {
                    globetrotters.add(user);
                }
            });
        });
        return globetrotters;

    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }


}
