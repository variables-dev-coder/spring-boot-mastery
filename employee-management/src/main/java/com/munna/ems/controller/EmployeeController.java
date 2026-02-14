package com.munna.ems.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.munna.ems.dto.EmployeeDTO;
import com.munna.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public EmployeeDTO create(@RequestBody EmployeeDTO dto) {
        return service.createEmployee(dto);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAll() {
        return service.getAllEmployees();
    }

    @PutMapping("/{id}")
    public EmployeeDTO update(@PathVariable Long id,
                              @RequestBody EmployeeDTO dto) {
        return service.updateEmployee(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEmployee(id);
    }
}
