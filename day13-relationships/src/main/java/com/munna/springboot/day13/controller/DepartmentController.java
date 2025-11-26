package com.munna.springboot.day13.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munna.springboot.day13.entity.Department;
import com.munna.springboot.day13.repository.DepartmentRepository;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	private final DepartmentRepository repo;

    public DepartmentController(DepartmentRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Department create(@RequestBody Department dept) {
        return repo.save(dept);
    }

    @GetMapping
    public List<Department> getAll() {
        return repo.findAll();
    }

}
