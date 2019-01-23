package com.smola.hiber.services;

import com.smola.hiber.exception.UserAlreadyExistsException;
import com.smola.hiber.exception.ResourceNotFoundException;
import com.smola.hiber.model.Route;
import com.smola.hiber.model.User;
import com.smola.hiber.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void shouldFindUserById() {
        // given
        User user = new User();
        user.setFirstName("Marcin");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        // when
        User userById = userService.findUserById("1");

        // then
        assertEquals(user, userById);
    }


    @Test(expected = ResourceNotFoundException.class)
    public void shouldThrowException_whenUserDoesNotExist() {
        //given
        when(userRepository.findById("1")).thenReturn(Optional.empty());

        //when - then
        userService.findUserById("112");
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void shouldThrowException_WhenUserAlreadyExists() {
        // given
        String existingEmail = "marcin@marcin.com";
        User user = new User();
        user.setFirstName("Marcin");
        user.setEmail(existingEmail);
        when(userRepository.findByEmail(existingEmail)).thenReturn(Optional.of(user));

        // when - then
        userService.createUser(user);
    }

    @Test
    public void shouldRetrieveRoutesCreatedByUser() {
        // given
        Route gubalowka = new Route("Gubalowka");
        Route giewont = new Route("Giewont");

        User firstUser = new User();
        firstUser.setFirstName("Marcin");
        firstUser.setLastName("Smola");
        firstUser.addCreatedRoute(gubalowka);
        firstUser.addCreatedRoute(giewont);
        firstUser.addTravelledRoute(giewont);

        when(userRepository.findById("1")).thenReturn(Optional.of(firstUser));

        // when - then
        Collection<Route> routesCreatedByUser = userService.findRoutesCreatedByUser("1");
        assertEquals(new HashSet<>(Arrays.asList(giewont,gubalowka)), routesCreatedByUser);
    }

    @Test
    public void shouldRetrieveUsersWhoTravelledRoute() {
        // given
        Route gubalowka = new Route("Gubalowka");
        Route giewont = new Route("Giewont");
        Route kasprowy = new Route("Kasprowy Wierch");

        User firstUser = new User();
        firstUser.setFirstName("Marcin");
        firstUser.setLastName("Smola");
        firstUser.addTravelledRoute(gubalowka);
        firstUser.addTravelledRoute(giewont);
        firstUser.addTravelledRoute(kasprowy);
        User secondUser = new User();
        secondUser.setFirstName("Mateusz");
        secondUser.setLastName("Tapa");
        secondUser.addTravelledRoute(gubalowka);

        when(userRepository.findAll()).thenReturn(Arrays.asList(firstUser,secondUser));

        // when - then
        assertEquals(Arrays.asList(firstUser,secondUser),userService.retrieveUsersTravelled(gubalowka.getName()));
        assertEquals(Arrays.asList(firstUser),userService.retrieveUsersTravelled(giewont.getName()));
    }

    @Test
    public void shouldUpdateUserRoutes() {
        // given
        User user = new User();
        user.setFirstName("Marcin");
        Route gubalowka = new Route("Gubalowka");
        Route kasprowy = new Route("Kasprowy");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        // when
        userService.updateUserRoutes("1", gubalowka, true);
        userService.updateUserRoutes("1",kasprowy,false);

        // then
        assertThat(user.getRoutesTravelled()).contains(gubalowka);
        assertThat(user.getRoutesCreated()).contains(gubalowka,kasprowy);

    }

    @Test
    public void shouldAddSameRouteOnlyOnce() {
        User user = new User();
        user.setFirstName("Marcin");
        Route gubalowka = new Route("Gubalowka");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        // when
        userService.updateUserRoutes("1", gubalowka, true);
        userService.updateUserRoutes("1", gubalowka, false);
        userService.updateUserRoutes("1", gubalowka, true);
        userService.updateUserRoutes("1", gubalowka, false);
        userService.updateUserRoutes("1", gubalowka, true);
        userService.updateUserRoutes("1", gubalowka, false);

        // then
        assertThat(user.getRoutesCreated()).size().isEqualTo(1);
        assertThat(user.getRoutesTravelled()).size().isEqualTo(1);

    }
}