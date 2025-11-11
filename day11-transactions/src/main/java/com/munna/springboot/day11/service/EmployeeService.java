package com.munna.springboot.day11.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munna.springboot.day11.model.Employee;
import com.munna.springboot.day11.repo.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee save(Employee e) {
        return repo.save(e);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // 1. Basic Transactional Example
    @Transactional
    public void saveTwoEmployees(Employee e1, Employee e2) {
        repo.save(e1);
        // Simulating an exception after the first save
        if (e2.getName() == null)
            throw new RuntimeException("Second employee name cannot be null!");
        repo.save(e2);
    }

    // 2. Rollback Test for Checked Exception
    @Transactional(rollbackFor = Exception.class)
    public void saveWithCheckedException(Employee e1, Employee e2) throws Exception {
        repo.save(e1);
        throw new Exception("Checked Exception – forcing rollback!");
    }
}

