package com.tasktracker.tasktracker.controller;


import com.tasktracker.tasktracker.dto.TaskRequest;
import com.tasktracker.tasktracker.dto.TaskResponse;
import com.tasktracker.tasktracker.entity.TaskStatus;
import com.tasktracker.tasktracker.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/tasks")
public class TaskController {



    private final TaskService taskService;



    public TaskController(TaskService taskService){

        this.taskService = taskService;

    }





    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest request
    ){

        return new ResponseEntity<>(
                taskService.createTask(request),
                HttpStatus.CREATED
        );

    }





    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks(

            @RequestParam(required = false)
            TaskStatus status,


            @RequestParam(required = false)
            Long owner_id

    ){

        return ResponseEntity.ok(

                taskService.getAllTasks(
                        status,
                        owner_id
                )

        );

    }





    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(

            @PathVariable Long id

    ){

        return ResponseEntity.ok(

                taskService.getTask(id)

        );

    }





    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(

            @PathVariable Long id,

            @RequestBody TaskRequest request

    ){

        return ResponseEntity.ok(

                taskService.updateTask(
                        id,
                        request
                )

        );

    }





    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(

            @PathVariable Long id

    ){

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();

    }


}