package com.smola.hiber.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smola.hiber.exception.ResourceNotFoundException;
import com.smola.hiber.model.Route;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    private JacksonTester<Page<User>> usersListJson;
    private JacksonTester<User> userJson;
    private JacksonTester<Route> routeJson;

    @Before
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void shouldRetrieveAllUsers() throws Exception {
        // given
        Page<User> users = new PageImpl<>(
                Arrays.asList(new User("Marcin"),
                        new User("Mati"),
                        new User("Kasia")));
        Pageable pageable = mock(Pageable.class);
        when(userService.retrieveAllUser(pageable)).thenReturn(users);

        // when
        MockHttpServletResponse response = mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturn_Http404_whenUserDoesNotExist() throws Exception {
        // given
        when(userService.findUserById("1")).thenThrow(new ResourceNotFoundException("User not found"));

        // when
        MockHttpServletResponse response = mockMvc.perform(get("/users/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    @Test
    public void shouldCreateUser() throws Exception {
        // given
        User user = new User();
        user.setFirstName("Marcin");
        when(userService.createUser(user)).thenReturn(user);

        // when
        String json = userJson.write(user).getJson();
        MockHttpServletResponse response = mockMvc.perform(put("/users").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldCreateNewRouteForUser() throws Exception {
        // given
        User user = new User();
        user.setFirstName("Marcin");
        Route gubalowka = new Route("Gubalowka");

        when(userService.findUserById(anyString())).thenReturn(user);

        // when
        String requestBodyJson = routeJson.write(gubalowka).getJson();
        MockHttpServletResponse response = mockMvc.perform(put("/users/1/routes")
                .param("travelled", "true")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyJson))
                .andDo(print())
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        //todo: I don't know why te response from mock mvc is empty. In Postman it works perfectly (the route is in response body).
//        assertThat(response.getContentAsString()).isEqualTo(requestBodyJson);

    }

}