package com.smola.hiber.services;

import com.smola.hiber.model.RouteSQL;
import com.smola.hiber.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }


    @Override
    public Iterable<RouteSQL> retrieveAllRoutes() {
        return this.routeRepository.findAll();
    }
}
