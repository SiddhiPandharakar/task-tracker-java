package com.tasktracker.tasktracker.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UserRequestTest {


    @Test
    void constructorShouldWork() {


        UserRequest request =
                new UserRequest(
                        "John",
                        "john@test.com"
                );


        assertEquals(
                "John",
                request.getName()
        );


        assertEquals(
                "john@test.com",
                request.getEmail()
        );

    }





    @Test
    void settersAndGettersShouldWork() {


        UserRequest request =
                new UserRequest();


        request.setName(
                "Alex"
        );


        request.setEmail(
                "alex@test.com"
        );


        assertEquals(
                "Alex",
                request.getName()
        );


        assertEquals(
                "alex@test.com",
                request.getEmail()
        );

    }

}