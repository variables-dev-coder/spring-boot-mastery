package com.munna.loginsignup.service;

import org.springframework.stereotype.Service;

import com.munna.loginsignup.dto.LoginRequest;
import com.munna.loginsignup.dto.SignupRequest;
import com.munna.loginsignup.entity.User;
import com.munna.loginsignup.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public String signup(SignupRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // No encryption
        user.setRole(request.getRole());

        userRepository.save(user);
        return "Signup successful";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return "User not found";
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return "Invalid password";
        }

        return "Login successful as " + user.getRole();
    }
}
