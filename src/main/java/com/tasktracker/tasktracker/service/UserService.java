package com.tasktracker.tasktracker.service;


import com.tasktracker.tasktracker.dto.UserRequest;
import com.tasktracker.tasktracker.dto.UserResponse;
import com.tasktracker.tasktracker.entity.User;
import com.tasktracker.tasktracker.exception.ResourceNotFoundException;
import com.tasktracker.tasktracker.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;



    public UserResponse createUser(UserRequest request){


        User user = new User(
                request.getName(),
                request.getEmail()
        );


        return mapToResponse(
                userRepository.save(user)
        );

    }






    public UserResponse getUserById(Long id){


        User user =
                userRepository.findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "User not found with id : "+id
                                        )
                        );


        return mapToResponse(user);

    }





    private UserResponse mapToResponse(User user){


        return new UserResponse(

                user.getId(),

                user.getName(),

                user.getEmail()

        );

    }

}