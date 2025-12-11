package com.munna.springboot.day17.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.munna.springboot.day17.entity.Employee;
import com.munna.springboot.day17.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
    private EmployeeRepository repo;

    // 1️ Get All Employees → Cache Result
    @Cacheable(value = "employees")
    public List<Employee> getAllEmployees() {
        simulateSlowService();
        return repo.findAll();
    }

    // 2️ Get by ID → Cache individual employee
    @Cacheable(value = "employees", key = "#id")
    public Employee getEmployeeById(Long id) {
        simulateSlowService();
        return repo.findById(id).orElse(null);
    }

    // 3️ Create Employee → Update cache
    @CachePut(value = "employees", key = "#employee.id")
    public Employee saveEmployee(Employee employee) {
        return repo.save(employee);
    }

    // 4️ Delete Employee → Remove from cache
    @CacheEvict(value = "employees", key = "#id")
    public void deleteEmployee(Long id) {
        repo.deleteById(id);
    }

    // For demonstration: adding delay to show caching works
    private void simulateSlowService() {
        try {
            Thread.sleep(2000); // 2 seconds delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
