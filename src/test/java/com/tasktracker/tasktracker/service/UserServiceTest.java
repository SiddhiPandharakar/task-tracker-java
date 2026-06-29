package com.tasktracker.tasktracker.service;

import com.tasktracker.tasktracker.dto.UserRequest;
import com.tasktracker.tasktracker.dto.UserResponse;
import com.tasktracker.tasktracker.entity.User;
import com.tasktracker.tasktracker.exception.ResourceNotFoundException;
import com.tasktracker.tasktracker.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void createUser_ShouldReturnUserResponse() {

        UserRequest request =
                new UserRequest("John","john@test.com");

        User savedUser =
                new User("John","john@test.com");

        savedUser.setId(1L);

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        UserResponse response =
                userService.createUser(request);

        assertEquals(1L,response.getId());
        assertEquals("John",response.getName());
    }

    @Test
    void getUserById_ShouldReturnUser() {

        User user =
                new User("John","john@test.com");

        user.setId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        UserResponse response =
                userService.getUserById(1L);

        assertEquals("John",response.getName());
    }

    @Test
    void getUserById_ShouldThrowException() {

        when(userRepository.findById(100L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getUserById(100L)
        );
    }
}