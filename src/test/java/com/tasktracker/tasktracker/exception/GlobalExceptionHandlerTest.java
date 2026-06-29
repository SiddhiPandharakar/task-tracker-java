package com.tasktracker.tasktracker.exception;


import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;


import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;



class GlobalExceptionHandlerTest {



    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();





    @Test
    void handleNotFound_ShouldReturn404(){


        ResourceNotFoundException exception =
                new ResourceNotFoundException(
                        "Task not found"
                );



        ResponseEntity<?> response =
                handler.handleNotFound(exception);



        assertEquals(
                404,
                response.getStatusCode().value()
        );



        Map<String,Object> body =
                (Map<String,Object>) response.getBody();



        assertNotNull(body);



        assertEquals(
                404,
                body.get("status")
        );



        assertEquals(
                "Task not found",
                body.get("message")
        );


        assertNotNull(
                body.get("timestamp")
        );

    }







    @Test
    void handleGeneral_ShouldReturn500(){



        ResponseEntity<?> response =
                handler.handleGeneral(
                        new RuntimeException(
                                "Error"
                        )
                );



        assertEquals(
                500,
                response.getStatusCode().value()
        );



        Map<String,Object> body =
                (Map<String,Object>) response.getBody();



        assertNotNull(body);



        assertEquals(
                500,
                body.get("status")
        );



        assertEquals(
                "Error",
                body.get("message")
        );


        assertNotNull(
                body.get("timestamp")
        );

    }








    @Test
    void handleValidation_ShouldReturn400(){



        Object target = new Object();



        BindingResult bindingResult =
                new BeanPropertyBindingResult(
                        target,
                        "task"
                );



        FieldError fieldError =
                new FieldError(
                        "task",
                        "title",
                        "Title is required"
                );



        bindingResult.addError(fieldError);




        MethodArgumentNotValidException exception =
                new MethodArgumentNotValidException(
                        null,
                        bindingResult
                );





        ResponseEntity<?> response =
                handler.handleValidation(exception);




        assertEquals(
                400,
                response.getStatusCode().value()
        );



        Map<String,Object> body =
                (Map<String,Object>) response.getBody();




        assertNotNull(body);



        assertEquals(
                400,
                body.get("status")
        );



        Map<String,String> errors =
                (Map<String,String>) body.get("errors");



        assertNotNull(errors);



        assertEquals(
                "Title is required",
                errors.get("title")
        );

    }

}