package com.munna.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.munna.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
