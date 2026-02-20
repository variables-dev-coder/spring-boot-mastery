package com.munna.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private double salary;
    private boolean active;
    private String departmentName;
}