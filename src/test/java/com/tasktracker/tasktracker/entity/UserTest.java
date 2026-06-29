package com.tasktracker.tasktracker.entity;


import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;



class UserTest {



    @Test
    void gettersAndSettersShouldWork() {


        User user =
                new User();



        user.setId(
                1L
        );


        user.setName(
                "John"
        );


        user.setEmail(
                "john@test.com"
        );



        assertEquals(
                1L,
                user.getId()
        );


        assertEquals(
                "John",
                user.getName()
        );


        assertEquals(
                "john@test.com",
                user.getEmail()
        );

    }







    @Test
    void constructorShouldWork() {


        User user =
                new User(
                        "John",
                        "john@test.com"
                );



        assertEquals(
                "John",
                user.getName()
        );


        assertEquals(
                "john@test.com",
                user.getEmail()
        );

    }







    @Test
    void tasksGetterAndSetterShouldWork() {


        User user =
                new User();



        List<Task> tasks =
                new ArrayList<>();



        Task task =
                new Task();



        tasks.add(task);



        user.setTasks(
                tasks
        );



        assertEquals(
                tasks,
                user.getTasks()
        );

    }

}