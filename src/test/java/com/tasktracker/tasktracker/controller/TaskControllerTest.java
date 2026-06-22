package com.tasktracker.tasktracker.controller;


import com.fasterxml.jackson.databind.ObjectMapper;


import com.tasktracker.tasktracker.dto.TaskRequest;
import com.tasktracker.tasktracker.dto.TaskResponse;

import com.tasktracker.tasktracker.entity.TaskStatus;

import com.tasktracker.tasktracker.service.TaskService;


import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;

import java.time.LocalDateTime;

import java.util.List;


import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(TaskController.class)
class TaskControllerTest {



    @Autowired
    MockMvc mockMvc;



    @Autowired
    ObjectMapper objectMapper;



    @MockBean
    TaskService taskService;







    private TaskResponse response(){


        return new TaskResponse(
                1L,
                "Task 1",
                "Description",
                LocalDate.now(),
                TaskStatus.TODO,
                LocalDateTime.now(),
                1L
        );

    }







    @Test
    void createTask_ShouldReturn201() throws Exception {



        TaskRequest request =
                new TaskRequest();



        request.setTitle(
                "Task 1"
        );


        request.setOwnerId(
                1L
        );



        when(taskService.createTask(any(TaskRequest.class)))
                .thenReturn(response());



        mockMvc.perform(
                        post("/tasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(
                        status().isCreated()
                )
                .andExpect(
                        jsonPath("$.title")
                                .value("Task 1")
                );

    }








    @Test
    void createTask_InvalidRequest_ShouldReturn400() throws Exception {


        TaskRequest request =
                new TaskRequest();



        mockMvc.perform(
                        post("/tasks")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(
                        status().isBadRequest()
                );

    }









    @Test
    void getTask_ShouldReturn200() throws Exception {



        when(taskService.getTask(1L))
                .thenReturn(response());



        mockMvc.perform(
                        get("/tasks/1")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.title")
                                .value("Task 1")
                );

    }









    @Test
    void getAllTasks_ShouldReturn200() throws Exception {



        when(taskService.getAllTasks(null,null))
                .thenReturn(
                        List.of(response())
                );



        mockMvc.perform(
                        get("/tasks")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$[0].title")
                                .value("Task 1")
                );

    }









    @Test
    void getAllTasks_WithFilters_ShouldReturn200() throws Exception {



        when(
                taskService.getAllTasks(
                        TaskStatus.TODO,
                        1L
                )
        )
                .thenReturn(
                        List.of(response())
                );



        mockMvc.perform(
                        get("/tasks")
                                .param(
                                        "status",
                                        "TODO"
                                )
                                .param(
                                        "owner_id",
                                        "1"
                                )
                )
                .andExpect(
                        status().isOk()
                );

    }









    @Test
    void updateTask_ShouldReturn200() throws Exception {



        TaskRequest request =
                new TaskRequest();


        request.setTitle(
                "Updated"
        );


        request.setOwnerId(
                1L
        );



        when(
                taskService.updateTask(
                        eq(1L),
                        any(TaskRequest.class)
                )
        )
                .thenReturn(response());



        mockMvc.perform(
                        put("/tasks/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(
                        status().isOk()
                );

    }








    @Test
    void deleteTask_ShouldReturn204() throws Exception {



        doNothing()
                .when(taskService)
                .deleteTask(1L);



        mockMvc.perform(
                        delete("/tasks/1")
                )
                .andExpect(
                        status().isNoContent()
                );

    }

}