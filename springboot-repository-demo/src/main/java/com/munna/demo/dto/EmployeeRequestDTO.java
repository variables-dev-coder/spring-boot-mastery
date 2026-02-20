package com.munna.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EmployeeRequestDTO {

    @NotBlank(message = "Name must not be blank")
    private String name;

    @Positive(message = "Salary must be greater than 0")
    private double salary;

    @NotNull(message = "Active status must be provided")
    private Boolean active;

    @NotNull(message = "DepartmentId must not be null")
    private Long departmentId;
}