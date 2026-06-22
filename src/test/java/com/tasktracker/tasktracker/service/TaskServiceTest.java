package com.tasktracker.tasktracker.service;


import com.tasktracker.tasktracker.dto.TaskRequest;
import com.tasktracker.tasktracker.dto.TaskResponse;
import com.tasktracker.tasktracker.entity.Task;
import com.tasktracker.tasktracker.entity.TaskStatus;
import com.tasktracker.tasktracker.entity.User;
import com.tasktracker.tasktracker.exception.ResourceNotFoundException;
import com.tasktracker.tasktracker.repository.TaskRepository;
import com.tasktracker.tasktracker.repository.UserRepository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class TaskServiceTest {



    @Mock
    TaskRepository taskRepository;


    @Mock
    UserRepository userRepository;



    @InjectMocks
    TaskService taskService;





    @Test
    void createTask_ShouldCreateTask() {


        User owner =
                new User(
                        "John",
                        "john@test.com"
                );

        owner.setId(1L);



        TaskRequest request =
                new TaskRequest();


        request.setTitle(
                "Task"
        );


        request.setOwnerId(
                1L
        );



        Task task =
                Task.builder()
                        .id(1L)
                        .title("Task")
                        .owner(owner)
                        .status(TaskStatus.TODO)
                        .createdAt(LocalDateTime.now())
                        .build();



        when(userRepository.findById(1L))
                .thenReturn(Optional.of(owner));



        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);



        TaskResponse response =
                taskService.createTask(request);



        assertEquals(
                "Task",
                response.getTitle()
        );

    }







    @Test
    void createTask_WithStatus_ShouldCreateTask() {


        User owner =
                new User(
                        "John",
                        "john@test.com"
                );


        owner.setId(1L);



        TaskRequest request =
                new TaskRequest();


        request.setTitle(
                "Task"
        );


        request.setOwnerId(
                1L
        );


        request.setStatus(
                TaskStatus.DONE
        );



        Task task =
                Task.builder()
                        .id(1L)
                        .title("Task")
                        .owner(owner)
                        .status(TaskStatus.DONE)
                        .createdAt(LocalDateTime.now())
                        .build();



        when(userRepository.findById(1L))
                .thenReturn(Optional.of(owner));



        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);



        TaskResponse response =
                taskService.createTask(request);



        assertEquals(
                TaskStatus.DONE,
                response.getStatus()
        );

    }








    @Test
    void createTask_OwnerNotFound() {


        TaskRequest request =
                new TaskRequest();


        request.setOwnerId(
                1L
        );



        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());



        assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.createTask(request)
        );

    }







    @Test
    void getTask_ShouldReturnTask() {


        User user =
                new User(
                        "John",
                        "john@test.com"
                );


        user.setId(1L);



        Task task =
                Task.builder()
                        .id(1L)
                        .title("Testing")
                        .owner(user)
                        .status(TaskStatus.TODO)
                        .createdAt(LocalDateTime.now())
                        .build();



        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));



        TaskResponse response =
                taskService.getTask(1L);



        assertEquals(
                "Testing",
                response.getTitle()
        );

    }







    @Test
    void getTask_NotFound() {


        when(taskRepository.findById(10L))
                .thenReturn(Optional.empty());



        assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.getTask(10L)
        );

    }








    @Test
    void getAllTasks_ShouldReturnList() {


        when(taskRepository.findAll())
                .thenReturn(List.of());



        List<TaskResponse> result =
                taskService.getAllTasks(
                        null,
                        null
                );



        assertNotNull(result);

    }







    @Test
    void getAllTasks_WithStatusAndOwner_ShouldReturnList() {


        when(taskRepository.findByStatusAndOwnerId(
                TaskStatus.TODO,
                1L
        ))
                .thenReturn(List.of());



        List<TaskResponse> result =
                taskService.getAllTasks(
                        TaskStatus.TODO,
                        1L
                );



        assertNotNull(result);



        verify(taskRepository)
                .findByStatusAndOwnerId(
                        TaskStatus.TODO,
                        1L
                );

    }








    @Test
    void getAllTasks_WithStatus_ShouldReturnList() {


        when(taskRepository.findByStatus(
                TaskStatus.DONE
        ))
                .thenReturn(List.of());



        List<TaskResponse> result =
                taskService.getAllTasks(
                        TaskStatus.DONE,
                        null
                );



        assertNotNull(result);



        verify(taskRepository)
                .findByStatus(
                        TaskStatus.DONE
                );

    }








    @Test
    void getAllTasks_WithOwner_ShouldReturnList() {


        when(taskRepository.findByOwnerId(1L))
                .thenReturn(List.of());



        List<TaskResponse> result =
                taskService.getAllTasks(
                        null,
                        1L
                );



        assertNotNull(result);



        verify(taskRepository)
                .findByOwnerId(1L);

    }








    @Test
    void updateTask_ShouldUpdate() {


        User user =
                new User(
                        "John",
                        "john@test.com"
                );


        user.setId(1L);



        Task task =
                Task.builder()
                        .id(1L)
                        .title("Old")
                        .owner(user)
                        .status(TaskStatus.TODO)
                        .createdAt(LocalDateTime.now())
                        .build();



        TaskRequest request =
                new TaskRequest();


        request.setTitle(
                "New"
        );



        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));



        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);



        TaskResponse response =
                taskService.updateTask(
                        1L,
                        request
                );



        assertNotNull(response);



        verify(taskRepository)
                .save(task);

    }








    @Test
    void updateTask_WithStatus_ShouldUpdateStatus() {


        User user =
                new User(
                        "John",
                        "john@test.com"
                );


        user.setId(1L);



        Task task =
                Task.builder()
                        .id(1L)
                        .title("Old")
                        .owner(user)
                        .status(TaskStatus.TODO)
                        .createdAt(LocalDateTime.now())
                        .build();



        TaskRequest request =
                new TaskRequest();


        request.setTitle(
                "New"
        );


        request.setStatus(
                TaskStatus.DONE
        );



        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));



        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);



        TaskResponse response =
                taskService.updateTask(
                        1L,
                        request
                );



        assertEquals(
                TaskStatus.DONE,
                response.getStatus()
        );

    }








    @Test
    void updateTask_NotFound() {


        when(taskRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.updateTask(
                        99L,
                        new TaskRequest()
                )
        );

    }








    @Test
    void deleteTask_ShouldDelete() {


        User user =
                new User(
                        "John",
                        "john@test.com"
                );


        user.setId(1L);



        Task task =
                Task.builder()
                        .id(1L)
                        .owner(user)
                        .build();



        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));



        taskService.deleteTask(1L);



        verify(taskRepository)
                .delete(task);

    }








    @Test
    void deleteTask_NotFound() {


        when(taskRepository.findById(99L))
                .thenReturn(Optional.empty());



        assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.deleteTask(99L)
        );

    }

}