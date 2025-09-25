package com.munna.springboot.day5.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munna.springboot.day5.model.User;

@RestController
@RequestMapping("/users")    // base URL
public class UserController {
	
	
	// In-memory HashMap for storing users
    private Map<Integer, User> userMap = new HashMap<>();

    // CREATE - Add a new user
    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userMap.put(user.getId(), user);
        return "User added successfully with ID: " + user.getId();
    }
    
    // READ - Get user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userMap.get(id);
    }
    
    // READ - Get all users
    @GetMapping("/all")
    public Map<Integer, User> getAllUsers() {
        return userMap;
    }
    
    // UPDATE - Update existing user
    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User user) {
        if (userMap.containsKey(id)) {
            User existingUser = userMap.get(id);
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            return "User updated successfully with ID: " + id;
        } else {
            return "User not found with ID: " + id;
        }
    }
    
    // DELETE - Remove user by ID
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        if (userMap.containsKey(id)) {
            userMap.remove(id);
            return "User deleted successfully with ID: " + id;
        } else {
            return "User not found with ID: " + id;
        }
    }
}
