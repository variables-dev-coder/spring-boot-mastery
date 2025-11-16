package com.munna.springboot.ems.service.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.munna.springboot.ems.model.Employee;
import com.munna.springboot.ems.repository.EmployeeRepository;
import com.munna.springboot.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee updateEmployee(Long id, Employee emp) {
        Employee e = getEmployeeById(id);

        e.setName(emp.getName());
        e.setEmail(emp.getEmail());
        e.setDepartment(emp.getDepartment());
        e.setSalary(emp.getSalary());

        return repository.save(e);
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }
}
