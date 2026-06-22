package com.tasktracker.tasktracker;


import org.junit.jupiter.api.Test;


import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;



@SpringBootTest
class TasktrackerApplicationTests {



    @Test
    void contextLoads() {

    }






    @Test
    void mainMethodShouldRun() {


        assertDoesNotThrow(() -> {

            TasktrackerApplication.main(
                    new String[]{}
            );

        });

    }


}