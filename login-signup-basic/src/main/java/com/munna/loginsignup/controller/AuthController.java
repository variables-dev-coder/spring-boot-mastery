package com.munna.loginsignup.controller;

import org.springframework.web.bind.annotation.*;

import com.munna.loginsignup.dto.LoginRequest;
import com.munna.loginsignup.dto.SignupRequest;
import com.munna.loginsignup.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
