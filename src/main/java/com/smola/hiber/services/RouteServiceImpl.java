package com.smola.hiber.services;

import com.smola.hiber.exception.ExceptionMessage;
import com.smola.hiber.exception.ResourceNotFoundException;
import com.smola.hiber.model.Route;
import com.smola.hiber.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }


    @Override
    public Page<Route> retrieveAllRoutes(Pageable pageable) {
        return this.routeRepository.findAll(pageable);
    }

    @Override
    public Route findById(String routeID) {
        return this.routeRepository.findById(routeID).orElseThrow(() -> new ResourceNotFoundException(ExceptionMessage.ROUTE_DOES_NOT_EXIST));
    }

    @Override
    public Route update(Route route) {
        return this.routeRepository.save(route);
    }


}
