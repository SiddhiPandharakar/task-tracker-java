package com.tasktracker.tasktracker.dto;


import com.tasktracker.tasktracker.entity.TaskStatus;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;



class TaskRequestTest {



    @Test
    void settersAndGettersShouldWork() {


        TaskRequest request =
                new TaskRequest();



        LocalDate dueDate =
                LocalDate.of(
                        2026,
                        1,
                        1
                );



        request.setTitle(
                "Task"
        );


        request.setDescription(
                "Description"
        );


        request.setDueDate(
                dueDate
        );


        request.setStatus(
                TaskStatus.TODO
        );


        request.setOwnerId(
                10L
        );



        assertEquals(
                "Task",
                request.getTitle()
        );


        assertEquals(
                "Description",
                request.getDescription()
        );


        assertEquals(
                dueDate,
                request.getDueDate()
        );


        assertEquals(
                TaskStatus.TODO,
                request.getStatus()
        );


        assertEquals(
                10L,
                request.getOwnerId()
        );

    }



}