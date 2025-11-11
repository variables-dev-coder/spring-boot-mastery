package com.munna.springboot.day11.controller;

import com.munna.springboot.day11.model.Employee;
import com.munna.springboot.day11.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    @PostMapping
    public Employee add(@RequestBody Employee e) {
        return service.save(e);
    }

    @PostMapping("/two")
    public String addTwo(@RequestBody List<Employee> employees) {
        try {
            service.saveTwoEmployees(employees.get(0), employees.get(1));
            return "Both employees saved!";
        } catch (Exception ex) {
            return "Transaction rolled back: " + ex.getMessage();
        }
    }

    @PostMapping("/checked")
    public String addTwoChecked(@RequestBody List<Employee> employees) {
        try {
            service.saveWithCheckedException(employees.get(0), employees.get(1));
            return "Both saved (should not happen)";
        } catch (Exception ex) {
            return "Rolled back due to checked exception: " + ex.getMessage();
        }
    }
}
