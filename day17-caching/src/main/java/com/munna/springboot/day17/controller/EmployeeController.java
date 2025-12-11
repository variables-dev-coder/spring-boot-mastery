package com.munna.springboot.day17.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munna.springboot.day17.entity.Employee;
import com.munna.springboot.day17.service.EmployeeService;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	 @Autowired
	    private EmployeeService service;

	    @GetMapping
	    public List<Employee> getAll() {
	        return service.getAllEmployees();
	    }

	    @GetMapping("/{id}")
	    public Employee getById(@PathVariable Long id) {
	        return service.getEmployeeById(id);
	    }

	    @PostMapping
	    public Employee create(@RequestBody Employee emp) {
	        return service.saveEmployee(emp);
	    }

	    @DeleteMapping("/{id}")
	    public String delete(@PathVariable Long id) {
	        service.deleteEmployee(id);
	        return "Employee Deleted!";
	    }

}
