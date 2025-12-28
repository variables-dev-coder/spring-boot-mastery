package com.munna.springboot.day25.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public/hello")
    public String publicApi() {
        return "Public API - No Auth Required";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/profile")
    public String userProfile() {
        return "USER Profile Access";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "ADMIN Dashboard Access";
    }

    @Secured({ "ROLE_ADMIN", "ROLE_USER" })
    @GetMapping("/common/info")
    public String commonApi() {
        return "Common API using @Secured";
    }
}
