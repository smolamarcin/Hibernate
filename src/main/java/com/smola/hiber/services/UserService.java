package com.smola.hiber.services;

import com.smola.hiber.model.User;

public interface UserService {
    Iterable<User> retrieveAllUser();

    User findUserById(Long id);
}
