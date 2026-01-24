package com.munna.restdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.munna.restdemo.model.User;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(1, "Munna", "munna@gmail.com"));
        users.add(new User(2, "Alex", "alex@gmail.com"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public User updateUser(int id, User updatedUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                return user;
            }
        }
        return null;
    }

    public void deleteUser(int id) {
        users.removeIf(u -> u.getId() == id);
    }
}
