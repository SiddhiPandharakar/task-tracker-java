package com.tasktracker.tasktracker.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


public enum TaskStatus {


    TODO("todo"),

    IN_PROGRESS("in-progress"),

    DONE("done");


    private final String value;


    TaskStatus(String value){

        this.value=value;

    }


    @JsonValue
    public String getValue(){

        return value;

    }



    @JsonCreator
    public static TaskStatus fromValue(String value){

        for(TaskStatus status : TaskStatus.values()){

            if(status.value.equalsIgnoreCase(value)
                    || status.name().equalsIgnoreCase(value)){

                return status;

            }

        }


        throw new IllegalArgumentException(
                "Invalid task status: " + value
        );

    }

}