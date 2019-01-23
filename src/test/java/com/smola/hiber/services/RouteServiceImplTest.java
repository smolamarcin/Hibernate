package com.smola.hiber.services;

import com.smola.hiber.exception.ResourceNotFoundException;
import com.smola.hiber.repositories.RouteRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RouteServiceImplTest {
    private RouteServiceImpl routeService;

    @Mock
    private RouteRepository routeRepository;
    @Before
    public void setUp() {
        initMocks(this);
        this.routeService = new RouteServiceImpl(routeRepository);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowException_WhenRouteDoesNotExist() {
        // given
        when(routeRepository.findById(anyString())).thenReturn(Optional.empty());

        // when -then
        this.routeService.findById(anyString());
    }
}