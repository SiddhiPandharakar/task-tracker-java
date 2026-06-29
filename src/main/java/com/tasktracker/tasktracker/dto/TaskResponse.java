package com.tasktracker.tasktracker.dto;



import com.tasktracker.tasktracker.entity.TaskStatus;


import java.time.LocalDate;
import java.time.LocalDateTime;



public class TaskResponse {



    private Long id;


    private String title;


    private String description;


    private LocalDate dueDate;


    private TaskStatus status;


    private LocalDateTime createdAt;


    private Long ownerId;



    public TaskResponse(){

    }



    public TaskResponse(
            Long id,
            String title,
            String description,
            LocalDate dueDate,
            TaskStatus status,
            LocalDateTime createdAt,
            Long ownerId
    ){

        this.id=id;
        this.title=title;
        this.description=description;
        this.dueDate=dueDate;
        this.status=status;
        this.createdAt=createdAt;
        this.ownerId=ownerId;

    }




    public Long getId(){

        return id;

    }



    public void setId(Long id){

        this.id=id;

    }




    public String getTitle(){

        return title;

    }



    public void setTitle(String title){

        this.title=title;

    }




    public String getDescription(){

        return description;

    }



    public void setDescription(String description){

        this.description=description;

    }




    public LocalDate getDueDate(){

        return dueDate;

    }



    public void setDueDate(LocalDate dueDate){

        this.dueDate=dueDate;

    }




    public TaskStatus getStatus(){

        return status;

    }



    public void setStatus(TaskStatus status){

        this.status=status;

    }




    public LocalDateTime getCreatedAt(){

        return createdAt;

    }



    public void setCreatedAt(LocalDateTime createdAt){

        this.createdAt=createdAt;

    }




    public Long getOwnerId(){

        return ownerId;

    }



    public void setOwnerId(Long ownerId){

        this.ownerId=ownerId;

    }

}