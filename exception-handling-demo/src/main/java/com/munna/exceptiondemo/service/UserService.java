package com.munna.exceptiondemo.service;

import com.munna.exceptiondemo.dto.UserRequest;
import com.munna.exceptiondemo.entity.User;
import com.munna.exceptiondemo.exception.DuplicateResourceException;
import com.munna.exceptiondemo.exception.ResourceNotFoundException;
import com.munna.exceptiondemo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest request) {

        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new DuplicateResourceException("Email already exists");
                });

        User user = new User(request.getName(), request.getEmail());
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + id));
    }
}
