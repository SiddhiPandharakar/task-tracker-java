package com.tasktracker.tasktracker.controller;

import com.tasktracker.tasktracker.repository.TaskRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MetricsController.class)
class MetricsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TaskRepository taskRepository;

    @Test
    void metrics_ShouldReturnTaskCount() throws Exception {

        when(taskRepository.count())
                .thenReturn(5L);

        mockMvc.perform(get("/metrics"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.tasks_total")
                                .value(5)
                );
    }
}