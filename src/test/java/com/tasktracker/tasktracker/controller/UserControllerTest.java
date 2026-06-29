package com.tasktracker.tasktracker.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.tasktracker.tasktracker.dto.UserRequest;
import com.tasktracker.tasktracker.dto.UserResponse;
import com.tasktracker.tasktracker.exception.ResourceNotFoundException;
import com.tasktracker.tasktracker.service.UserService;


import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(UserController.class)
class UserControllerTest {



    @Autowired
    MockMvc mockMvc;



    @Autowired
    ObjectMapper objectMapper;



    @MockitoBean
    UserService userService;





    @Test
    void createUser_ShouldReturn201() throws Exception {


        UserRequest request =
                new UserRequest(
                        "John",
                        "john@test.com"
                );



        UserResponse response =
                new UserResponse(
                        1L,
                        "John",
                        "john@test.com"
                );



        when(userService.createUser(any(UserRequest.class)))
                .thenReturn(response);



        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(
                        status().isCreated()
                )
                .andExpect(
                        jsonPath("$.name")
                                .value("John")
                );

    }







    @Test
    void getUser_ShouldReturn200() throws Exception {



        when(userService.getUserById(1L))
                .thenReturn(
                        new UserResponse(
                                1L,
                                "John",
                                "john@test.com"
                        )
                );



        mockMvc.perform(
                        get("/users/1")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.email")
                                .value("john@test.com")
                );

    }








    @Test
    void getUser_NotFound_ShouldReturn404() throws Exception {



        when(userService.getUserById(99L))
                .thenThrow(
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );



        mockMvc.perform(
                        get("/users/99")
                )
                .andExpect(
                        status().isNotFound()
                );

    }

}