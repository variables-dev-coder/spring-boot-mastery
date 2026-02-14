package com.munna.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munna.ems.dto.EmployeeDTO;
import com.munna.ems.entity.Employee;
import com.munna.ems.exception.ResourceNotFoundException;
import com.munna.ems.repository.EmployeeRepository;
import com.munna.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee employee = mapToEntity(dto);
        return mapToDTO(repository.save(employee));
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return mapToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setDepartment(dto.getDepartment());
        employee.setSalary(dto.getSalary());

        return mapToDTO(employee); // Dirty checking works
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    private Employee mapToEntity(EmployeeDTO dto) {
        return Employee.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .department(dto.getDepartment())
                .salary(dto.getSalary())
                .build();
    }

    private EmployeeDTO mapToDTO(Employee entity) {
        return EmployeeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .department(entity.getDepartment())
                .salary(entity.getSalary())
                .build();
    }
}
