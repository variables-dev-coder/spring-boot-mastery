package com.munna.springboot.ems.service;

import java.util.List;

import com.munna.springboot.ems.model.Employee;

public interface EmployeeService {
	
	Employee saveEmployee(Employee employee);
	Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);

}
