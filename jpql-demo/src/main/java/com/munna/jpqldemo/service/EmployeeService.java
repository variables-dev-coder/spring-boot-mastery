package com.munna.jpqldemo.service;

import com.munna.jpqldemo.entity.Employee;
import com.munna.jpqldemo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getByDepartment(String dept) {
        return repository.findByDepartment(dept);
    }

    public List<Employee> getBySalaryRange(double min, double max) {
        return repository.findBySalaryRange(min, max);
    }

    public Long countByDepartment(String dept) {
        return repository.countByDepartment(dept);
    }

    public int updateSalary(Long id, double salary) {
        return repository.updateSalary(id, salary);
    }
}