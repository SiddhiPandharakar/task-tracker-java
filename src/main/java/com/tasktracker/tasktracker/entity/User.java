package com.tasktracker.tasktracker.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotBlank(message="Name is required")
    @Column(nullable=false)
    private String name;



    @NotBlank(message="Email is required")
    @Email(message="Email should be valid")
    @Column(
            unique=true,
            nullable=false
    )
    private String email;



    @OneToMany(
            mappedBy="owner",
            cascade=CascadeType.ALL,
            orphanRemoval=true,
            fetch = FetchType.LAZY
    )
    private List<Task> tasks = new ArrayList<>();



    public User(){

    }



    public User(String name,String email){

        this.name=name;
        this.email=email;

    }




    public Long getId(){

        return id;

    }


    public void setId(Long id){

        this.id=id;

    }



    public String getName(){

        return name;

    }


    public void setName(String name){

        this.name=name;

    }



    public String getEmail(){

        return email;

    }


    public void setEmail(String email){

        this.email=email;

    }



    public List<Task> getTasks(){

        return tasks;

    }


    public void setTasks(List<Task> tasks){

        this.tasks=tasks;

    }

}