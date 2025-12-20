package com.munna.springboot.day22.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class TestController {
	
	@GetMapping("/public/welcome")
    public String welcome() {
        return "Welcome! Public Access";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "User Dashboard";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Admin Dashboard";
    }

}

/*

Spring Security default behavior
Authentication vs Authorization
Role-based access
Custom UserDetailsService
BCrypt password encryption
Industry-level package structure


*/