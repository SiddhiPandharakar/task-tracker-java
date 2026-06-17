package com.tasktracker.tasktracker.controller;


import com.tasktracker.tasktracker.dto.UserRequest;
import com.tasktracker.tasktracker.dto.UserResponse;
import com.tasktracker.tasktracker.service.UserService;


import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {



    private final UserService userService;





    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserRequest request
    ){


        return new ResponseEntity<>(

                userService.createUser(request),

                HttpStatus.CREATED

        );


    }








    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(

            @PathVariable Long id

    ){


        return ResponseEntity.ok(

                userService.getUserById(id)

        );


    }



}