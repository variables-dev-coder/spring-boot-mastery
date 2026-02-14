package com.munna.ems.service;

import java.util.List;
import com.munna.ems.dto.EmployeeDTO;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO dto);

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);

    void deleteEmployee(Long id);
}
