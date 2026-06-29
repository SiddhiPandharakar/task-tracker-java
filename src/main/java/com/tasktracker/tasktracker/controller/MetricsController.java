package com.tasktracker.tasktracker.controller;


import com.tasktracker.tasktracker.repository.TaskRepository;

import org.springframework.web.bind.annotation.*;


import java.util.Map;



@RestController
@RequestMapping("/metrics")
public class MetricsController {


    private final TaskRepository taskRepository;



    public MetricsController(TaskRepository taskRepository){

        this.taskRepository = taskRepository;

    }



    @GetMapping
    public Map<String,Object> metrics(){


        return Map.of(

                "tasks_total",

                taskRepository.count()

        );

    }

}