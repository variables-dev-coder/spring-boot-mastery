package com.example.userversioning.controller.v1;

import com.example.userversioning.dto.v1.UserV1Response;
import com.example.userversioning.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserV1Controller {

    private final UserService userService;

    public UserV1Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserV1Response> usersV1(HttpServletResponse response) {

        // Deprecation headers
        response.setHeader("X-API-DEPRECATED", "true");
        response.setHeader("X-API-SUNSET", "2026-12-31");
        response.setHeader("X-API-REPLACEMENT", "/api/v2/users");

        return userService.getUsers()
                .stream()
                .map(user -> new UserV1Response(
                        user.getId(),
                        user.getName()
                ))
                .collect(Collectors.toList());
    }
}
