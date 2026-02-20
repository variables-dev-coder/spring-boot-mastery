package com.munna.demo.service;

import com.munna.demo.dto.EmployeeRequestDTO;
import com.munna.demo.dto.EmployeeResponseDTO;
import com.munna.demo.entity.Department;
import com.munna.demo.entity.Employee;
import com.munna.demo.repository.DepartmentRepository;
import com.munna.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munna.demo.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {

    	Department department = departmentRepository
    	        .findById(dto.getDepartmentId())
    	        .orElseThrow(() -> 
    	            new ResourceNotFoundException("Department not found with id: " + dto.getDepartmentId()));

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setSalary(dto.getSalary());
        employee.setActive(dto.getActive());
        employee.setDepartment(department);

        Employee saved = employeeRepository.save(employee);

        return EmployeeResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .salary(saved.getSalary())
                .active(saved.isActive())
                .departmentName(saved.getDepartment().getName())
                .build();
    }
}