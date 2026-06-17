package com.tasktracker.tasktracker.entity;


import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name="tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable=false)
    private String title;


    private String description;


    private LocalDate dueDate;


    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    @Builder.Default
    private TaskStatus status = TaskStatus.TODO;


    @Column(nullable=false)
    private LocalDateTime createdAt;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name="owner_id",
            nullable=false
    )
    private User owner;



    @PrePersist
    public void beforeSave(){

        if(createdAt == null){
            createdAt = LocalDateTime.now();
        }


        if(status == null){
            status = TaskStatus.TODO;
        }

    }

}