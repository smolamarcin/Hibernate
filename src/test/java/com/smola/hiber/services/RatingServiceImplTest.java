package com.smola.hiber.services;

import com.smola.hiber.exception.ResourceNotFoundException;
import com.smola.hiber.model.Route;
import com.smola.hiber.repositories.RouteRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RatingServiceImplTest {
    @Mock
    private UserService userService;
    @Mock
    private RouteService routeService;

    private RatingServiceImpl ratingService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.ratingService = new RatingServiceImpl(routeService,userService);
    }

    @Test
    public void shouldCalculateAverageRateForRoute() {
        //given
        Route route = new Route("K2");
        route.setRate(5);
        when(routeService.findById(anyString())).thenReturn(route);

        //when
        ratingService.rateRoute("user id","route id", 4);

        //then
        assertThat(route.getRating()).isEqualTo(4.5);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowException_whenTryingToRateNotExistingRoute() {
        //given
        when(routeService.findById(anyString())).thenThrow(ResourceNotFoundException.class);

        //when -ten
        ratingService.rateRoute("1", "2", 2.5);
    }

}