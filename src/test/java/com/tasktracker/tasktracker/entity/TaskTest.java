package com.tasktracker.tasktracker.entity;


import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.*;



class TaskTest {



    @Test
    void taskBuilderTest(){


        User user =
                new User(
                        "John",
                        "john@test.com"
                );


        user.setId(1L);



        Task task =
                Task.builder()
                        .id(1L)
                        .title("Test")
                        .description("Demo")
                        .dueDate(
                                LocalDate.of(
                                        2026,
                                        1,
                                        1
                                )
                        )
                        .status(TaskStatus.TODO)
                        .createdAt(
                                LocalDateTime.now()
                        )
                        .owner(user)
                        .build();



        assertEquals(
                1L,
                task.getId()
        );


        assertEquals(
                "Test",
                task.getTitle()
        );


        assertEquals(
                "Demo",
                task.getDescription()
        );


        assertEquals(
                TaskStatus.TODO,
                task.getStatus()
        );


        assertEquals(
                user,
                task.getOwner()
        );

    }







    @Test
    void settersAndGettersShouldWork(){


        Task task =
                new Task();



        LocalDate date =
                LocalDate.of(
                        2026,
                        2,
                        1
                );


        LocalDateTime time =
                LocalDateTime.of(
                        2026,
                        2,
                        1,
                        10,
                        30
                );



        User user =
                new User(
                        "Alex",
                        "alex@test.com"
                );



        task.setId(10L);

        task.setTitle(
                "New Task"
        );

        task.setDescription(
                "Testing"
        );

        task.setDueDate(
                date
        );

        task.setStatus(
                TaskStatus.DONE
        );

        task.setCreatedAt(
                time
        );

        task.setOwner(
                user
        );



        assertEquals(
                10L,
                task.getId()
        );


        assertEquals(
                "New Task",
                task.getTitle()
        );


        assertEquals(
                "Testing",
                task.getDescription()
        );


        assertEquals(
                date,
                task.getDueDate()
        );


        assertEquals(
                TaskStatus.DONE,
                task.getStatus()
        );


        assertEquals(
                time,
                task.getCreatedAt()
        );


        assertEquals(
                user,
                task.getOwner()
        );

    }








    @Test
    void beforeSaveShouldSetCreatedAtAndDefaultStatus(){


        Task task =
                new Task();



        task.setStatus(null);

        task.setCreatedAt(null);



        task.beforeSave();



        assertNotNull(
                task.getCreatedAt()
        );


        assertEquals(
                TaskStatus.TODO,
                task.getStatus()
        );

    }







    @Test
    void beforeSaveShouldNotOverrideExistingValues(){


        LocalDateTime existingTime =
                LocalDateTime.of(
                        2026,
                        1,
                        1,
                        10,
                        0
                );



        Task task =
                new Task();



        task.setCreatedAt(
                existingTime
        );


        task.setStatus(
                TaskStatus.DONE
        );



        task.beforeSave();



        assertEquals(
                existingTime,
                task.getCreatedAt()
        );


        assertEquals(
                TaskStatus.DONE,
                task.getStatus()
        );

    }

}