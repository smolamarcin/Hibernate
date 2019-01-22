package com.smola.hiber.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smola.hiber.exception.UserNotFoundException;
import com.smola.hiber.model.User;
import com.smola.hiber.repositories.UserRepository;
import com.smola.hiber.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    private JacksonTester<Collection<User>> usersListJson;
    private JacksonTester<Collection<User>> userJson;

    @Before
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void shouldRetrieveAllUsers() throws Exception {
        // given
        List<User> users = Arrays.asList(new User("Marcin"),
                new User("Mati"),
                new User("Kasia"));
        when(userService.retrieveAllUser()).thenReturn(users);

        // when
        MockHttpServletResponse response = mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(usersListJson.write(users).getJson());
    }

    @Test
    public void shouldReturn_Http404_whenUserDoesNotExist() throws Exception {
        // given
        when(userService.findUserById(1L)).thenThrow(new UserNotFoundException("User not found"));

        // when
        MockHttpServletResponse response = mockMvc.perform(get("/users/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }
}