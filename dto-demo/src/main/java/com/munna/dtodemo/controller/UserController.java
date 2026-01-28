package com.munna.dtodemo.controller;

import com.munna.dtodemo.dto.request.UserCreateRequestDTO;
import com.munna.dtodemo.dto.response.UserResponseDTO;
import com.munna.dtodemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser(
            @Valid @RequestBody UserCreateRequestDTO dto) {
        return userService.createUser(dto);
    }
}
