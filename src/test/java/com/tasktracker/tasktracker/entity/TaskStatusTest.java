package com.tasktracker.tasktracker.entity;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;



class TaskStatusTest {


    @Test
    void enumValuesTest(){


        assertNotNull(
                TaskStatus.TODO
        );


        assertNotNull(
                TaskStatus.IN_PROGRESS
        );


        assertNotNull(
                TaskStatus.DONE
        );


    }





    @Test
    void getValue_ShouldReturnCorrectValue(){


        assertEquals(
                "todo",
                TaskStatus.TODO.getValue()
        );


        assertEquals(
                "in-progress",
                TaskStatus.IN_PROGRESS.getValue()
        );


        assertEquals(
                "done",
                TaskStatus.DONE.getValue()
        );


    }





    @Test
    void fromValue_ShouldConvertStringToEnum(){


        assertEquals(
                TaskStatus.TODO,
                TaskStatus.fromValue("todo")
        );


        assertEquals(
                TaskStatus.IN_PROGRESS,
                TaskStatus.fromValue("IN_PROGRESS")
        );


        assertEquals(
                TaskStatus.DONE,
                TaskStatus.fromValue("done")
        );


    }





    @Test
    void fromValue_InvalidStatus_ShouldThrowException(){


        assertThrows(
                IllegalArgumentException.class,
                () ->
                        TaskStatus.fromValue("invalid")
        );


    }

}