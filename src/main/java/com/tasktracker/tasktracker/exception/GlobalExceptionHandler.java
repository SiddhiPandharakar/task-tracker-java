package com.tasktracker.tasktracker.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;



@RestControllerAdvice
public class GlobalExceptionHandler {



    // ==============================
    // 404 NOT FOUND
    // ==============================

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(
            ResourceNotFoundException exception
    ){


        Map<String,Object> response = new HashMap<>();


        response.put(
                "timestamp",
                LocalDateTime.now()
        );


        response.put(
                "status",
                404
        );


        response.put(
                "message",
                exception.getMessage()
        );


        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );

    }




    // ==============================
    // VALIDATION ERROR
    // ==============================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(
            MethodArgumentNotValidException exception
    ){


        Map<String,String> errors =
                new HashMap<>();


        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {


                    errors.put(
                            error.getField(),
                            error.getDefaultMessage()
                    );


                });



        Map<String,Object> response =
                new HashMap<>();


        response.put(
                "timestamp",
                LocalDateTime.now()
        );


        response.put(
                "status",
                400
        );


        response.put(
                "errors",
                errors
        );



        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );


    }




    // ==============================
    // GENERIC ERROR
    // ==============================


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(
            Exception exception
    ){


        Map<String,Object> response =
                new HashMap<>();


        response.put(
                "timestamp",
                LocalDateTime.now()
        );


        response.put(
                "status",
                500
        );


        response.put(
                "message",
                exception.getMessage()
        );


        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );


    }

}