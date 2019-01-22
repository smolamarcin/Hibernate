package com.smola.hiber.services;

import com.smola.hiber.exception.UserNotFoundException;
import com.smola.hiber.model.UserSQL;
import com.smola.hiber.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserSQLServiceImplTest {
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
        UserSQL userSQL = new UserSQL();
        userSQL.setFirstName("Marcin");
        when(userRepository.findById(1L)).thenReturn(Optional.of(userSQL));

        // when
        UserSQL userSQLById = userService.findUserById(1L);

        // then
        assertEquals(userSQL, userSQLById);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowException_whenUserDoesNotExist() {
        //given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        //when - then
        userService.findUserById(1L);
    }


}