package com.munna.jpqldemo.controller;

import com.munna.jpqldemo.entity.Employee;
import com.munna.jpqldemo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @GetMapping("/department/{dept}")
    public List<Employee> byDepartment(@PathVariable String dept) {
        return service.getByDepartment(dept);
    }

    @GetMapping("/salary")
    public List<Employee> bySalaryRange(@RequestParam double min,
                                        @RequestParam double max) {
        return service.getBySalaryRange(min, max);
    }

    @GetMapping("/count/{dept}")
    public Long count(@PathVariable String dept) {
        return service.countByDepartment(dept);
    }

    @PutMapping("/{id}")
    public String updateSalary(@PathVariable Long id,
                               @RequestParam double salary) {
        service.updateSalary(id, salary);
        return "Salary Updated";
    }
}