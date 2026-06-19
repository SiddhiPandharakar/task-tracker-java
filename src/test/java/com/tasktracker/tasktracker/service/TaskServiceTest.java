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

import java.time.LocalDate;
import java.time.LocalDateTime;
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

        User owner = new User("John","john@test.com");
        owner.setId(1L);

        TaskRequest request = new TaskRequest();
        request.setTitle("Task");
        request.setOwnerId(1L);

        Task task = Task.builder()
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

        assertEquals("Task",response.getTitle());
    }

    @Test
    void createTask_OwnerNotFound() {

        TaskRequest request = new TaskRequest();

        request.setTitle("Task");
        request.setOwnerId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.createTask(request)
        );
    }

    @Test
    void getTask_NotFound() {

        when(taskRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.getTask(999L)
        );
    }
}