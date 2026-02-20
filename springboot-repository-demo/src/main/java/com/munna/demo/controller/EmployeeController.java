package com.munna.demo.controller;

import com.munna.demo.dto.EmployeeRequestDTO;
import com.munna.demo.dto.EmployeeResponseDTO;
import com.munna.demo.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public EmployeeResponseDTO create(
            @Valid @RequestBody EmployeeRequestDTO request) {
        return service.createEmployee(request);
    }
}