package com.tasktracker.tasktracker.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UserResponseTest {


    @Test
    void constructorShouldWork() {


        UserResponse response =
                new UserResponse(
                        1L,
                        "John",
                        "john@test.com"
                );


        assertEquals(
                1L,
                response.getId()
        );


        assertEquals(
                "John",
                response.getName()
        );


        assertEquals(
                "john@test.com",
                response.getEmail()
        );

    }





    @Test
    void settersAndGettersShouldWork() {


        UserResponse response =
                new UserResponse();


        response.setId(
                10L
        );


        response.setName(
                "David"
        );


        response.setEmail(
                "david@test.com"
        );


        assertEquals(
                10L,
                response.getId()
        );


        assertEquals(
                "David",
                response.getName()
        );


        assertEquals(
                "david@test.com",
                response.getEmail()
        );

    }

}