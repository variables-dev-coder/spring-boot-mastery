package com.munna.springboot.day13.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munna.springboot.day13.entity.Employee;
import com.munna.springboot.day13.repository.EmployeeRepository;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Employee create(@RequestBody Employee emp) {
        return repo.save(emp);
    }

    @GetMapping
    public List<Employee> getAll() {
        return repo.findAll();
    }

}
