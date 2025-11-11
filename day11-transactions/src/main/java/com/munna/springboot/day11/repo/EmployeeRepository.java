package com.munna.springboot.day11.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.munna.springboot.day11.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
