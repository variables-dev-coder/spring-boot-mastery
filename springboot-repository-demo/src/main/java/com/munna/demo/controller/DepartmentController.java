package com.munna.demo.controller;

import com.munna.demo.entity.Department;
import com.munna.demo.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository repository;

    @PostMapping
    public Department create(@RequestBody Department department) {
        return repository.save(department);
    }
}