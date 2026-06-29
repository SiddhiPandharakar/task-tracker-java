package com.tasktracker.tasktracker.dto;


import com.tasktracker.tasktracker.entity.TaskStatus;


import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.*;



class TaskResponseTest {



    @Test
    void constructorShouldWork() {


        LocalDate dueDate =
                LocalDate.of(
                        2026,
                        1,
                        1
                );


        LocalDateTime createdAt =
                LocalDateTime.of(
                        2026,
                        1,
                        1,
                        10,
                        30
                );



        TaskResponse response =
                new TaskResponse(
                        1L,
                        "Task",
                        "Description",
                        dueDate,
                        TaskStatus.TODO,
                        createdAt,
                        10L
                );



        assertEquals(
                1L,
                response.getId()
        );


        assertEquals(
                "Task",
                response.getTitle()
        );


        assertEquals(
                "Description",
                response.getDescription()
        );


        assertEquals(
                dueDate,
                response.getDueDate()
        );


        assertEquals(
                TaskStatus.TODO,
                response.getStatus()
        );


        assertEquals(
                createdAt,
                response.getCreatedAt()
        );


        assertEquals(
                10L,
                response.getOwnerId()
        );

    }







    @Test
    void settersAndGettersShouldWork() {


        TaskResponse response =
                new TaskResponse();



        LocalDate dueDate =
                LocalDate.of(
                        2026,
                        2,
                        1
                );


        LocalDateTime createdAt =
                LocalDateTime.of(
                        2026,
                        2,
                        1,
                        12,
                        0
                );



        response.setId(
                2L
        );


        response.setTitle(
                "New Task"
        );


        response.setDescription(
                "Testing"
        );


        response.setDueDate(
                dueDate
        );


        response.setStatus(
                TaskStatus.DONE
        );


        response.setCreatedAt(
                createdAt
        );


        response.setOwnerId(
                5L
        );



        assertEquals(
                2L,
                response.getId()
        );


        assertEquals(
                "New Task",
                response.getTitle()
        );


        assertEquals(
                "Testing",
                response.getDescription()
        );


        assertEquals(
                dueDate,
                response.getDueDate()
        );


        assertEquals(
                TaskStatus.DONE,
                response.getStatus()
        );


        assertEquals(
                createdAt,
                response.getCreatedAt()
        );


        assertEquals(
                5L,
                response.getOwnerId()
        );

    }

}