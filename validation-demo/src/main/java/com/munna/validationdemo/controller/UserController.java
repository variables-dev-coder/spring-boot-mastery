package com.munna.validationdemo.controller;

import com.munna.validationdemo.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(
            @Valid @RequestBody UserRequest request) {

        return ResponseEntity.ok("Validation successful");
    }
}
