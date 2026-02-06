package com.example.userversioning.service;

import com.example.userversioning.model.User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    public List<User> getUsers() {
        return Arrays.asList(
                new User(1L, "Munna"),
                new User(2L, "Alex")
        );
    }
}
