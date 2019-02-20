package com.smola.hiber.services;

import com.smola.hiber.exception.ExceptionMessage;
import com.smola.hiber.exception.UserAlreadyExistsException;
import com.smola.hiber.exception.ResourceNotFoundException;
import com.smola.hiber.model.Role;
import com.smola.hiber.model.Route;
import com.smola.hiber.model.User;
import com.smola.hiber.repositories.RoleRepository;
import com.smola.hiber.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl( UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
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
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
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

    @Override
    public User findUserByEmail(String email) {
        Optional<User> byEmail = this.userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            return byEmail.get();
        } else {
            return null;
        }

    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE));
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE));
        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
