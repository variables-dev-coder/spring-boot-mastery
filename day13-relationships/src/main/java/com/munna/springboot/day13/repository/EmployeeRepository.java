package com.munna.springboot.day13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.munna.springboot.day13.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
