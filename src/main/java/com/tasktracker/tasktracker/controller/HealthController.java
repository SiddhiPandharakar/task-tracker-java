package com.tasktracker.tasktracker.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



@RestController
public class HealthController {


    private final JdbcTemplate jdbcTemplate;



    public HealthController(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;

    }




    @GetMapping("/healthz")
    public ResponseEntity<?> health(){


        return ResponseEntity.ok(

                Map.of(
                        "status",
                        "ok"
                )

        );

    }






    @GetMapping("/readyz")
    public ResponseEntity<?> ready(){


        try{


            jdbcTemplate.queryForObject(
                    "SELECT 1",
                    Integer.class
            );


            return ResponseEntity.ok(

                    Map.of(
                            "status",
                            "ready"
                    )

            );


        }

        catch(Exception e){


            return ResponseEntity

                    .status(HttpStatus.SERVICE_UNAVAILABLE)

                    .body(

                            Map.of(
                                    "status",
                                    "not ready"
                            )

                    );


        }


    }


}