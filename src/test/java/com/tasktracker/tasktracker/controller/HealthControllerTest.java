package com.tasktracker.tasktracker.controller;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(HealthController.class)
class HealthControllerTest {


    @Autowired
    MockMvc mockMvc;


    @MockBean
    JdbcTemplate jdbcTemplate;



    @Test
    void health_ShouldReturnOk() throws Exception {


        mockMvc.perform(get("/healthz"))

                .andExpect(status().isOk())

                .andExpect(
                        jsonPath("$.status")
                                .value("ok")
                );

    }





    @Test
    void ready_ShouldReturnOk_WhenDatabaseWorks()
            throws Exception {


        when(
                jdbcTemplate.queryForObject(
                        "SELECT 1",
                        Integer.class
                )
        )
                .thenReturn(1);



        mockMvc.perform(get("/readyz"))

                .andExpect(status().isOk())

                .andExpect(
                        jsonPath("$.status")
                                .value("ready")
                );


    }






    @Test
    void ready_ShouldReturn503_WhenDatabaseFails()
            throws Exception {



        when(
                jdbcTemplate.queryForObject(
                        "SELECT 1",
                        Integer.class
                )
        )
                .thenThrow(new RuntimeException());



        mockMvc.perform(get("/readyz"))

                .andExpect(
                        status()
                                .isServiceUnavailable()
                )

                .andExpect(
                        jsonPath("$.status")
                                .value("not ready")
                );


    }

}