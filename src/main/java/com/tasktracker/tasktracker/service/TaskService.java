package com.tasktracker.tasktracker.service;


import com.tasktracker.tasktracker.dto.TaskRequest;
import com.tasktracker.tasktracker.dto.TaskResponse;

import com.tasktracker.tasktracker.entity.Task;
import com.tasktracker.tasktracker.entity.TaskStatus;
import com.tasktracker.tasktracker.entity.User;

import com.tasktracker.tasktracker.exception.ResourceNotFoundException;

import com.tasktracker.tasktracker.repository.TaskRepository;
import com.tasktracker.tasktracker.repository.UserRepository;


import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class TaskService {


    private final TaskRepository taskRepository;

    private final UserRepository userRepository;



    public TaskService(
            TaskRepository taskRepository,
            UserRepository userRepository
    ){

        this.taskRepository = taskRepository;
        this.userRepository = userRepository;

    }





    public TaskResponse createTask(TaskRequest request){


        User owner =
                userRepository.findById(request.getOwnerId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Owner not found"
                                )
                        );



        Task task = new Task();


        task.setTitle(request.getTitle());

        task.setDescription(request.getDescription());

        task.setDueDate(request.getDueDate());


        if(request.getStatus()!=null){

            task.setStatus(request.getStatus());

        }


        task.setOwner(owner);



        return mapToResponse(
                taskRepository.save(task)
        );

    }






    public List<TaskResponse> getAllTasks(
            TaskStatus status,
            Long ownerId
    ){


        List<Task> tasks;



        if(status != null && ownerId != null){


            tasks =
                    taskRepository.findByStatusAndOwnerId(
                            status,
                            ownerId
                    );


        }
        else if(status != null){


            tasks =
                    taskRepository.findByStatus(status);


        }
        else if(ownerId != null){


            tasks =
                    taskRepository.findByOwnerId(ownerId);


        }
        else{


            tasks =
                    taskRepository.findAll();

        }



        return tasks.stream()
                .map(this::mapToResponse)
                .toList();

    }







    public TaskResponse getTask(Long id){


        Task task =
                taskRepository.findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Task not found"
                                        )
                        );


        return mapToResponse(task);

    }








    public TaskResponse updateTask(
            Long id,
            TaskRequest request
    ){


        Task task =
                taskRepository.findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Task not found"
                                        )
                        );



        task.setTitle(request.getTitle());

        task.setDescription(request.getDescription());

        task.setDueDate(request.getDueDate());



        if(request.getStatus()!=null){

            task.setStatus(request.getStatus());

        }



        return mapToResponse(
                taskRepository.save(task)
        );

    }







    public void deleteTask(Long id){


        Task task =
                taskRepository.findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Task not found"
                                        )
                        );


        taskRepository.delete(task);


    }






    private TaskResponse mapToResponse(Task task){

        Long ownerId = null;

        if (task.getOwner() != null) {
            ownerId = task.getOwner().getId();
        }

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getStatus(),
                task.getCreatedAt(),
                ownerId
        );
    }

}