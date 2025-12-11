package com.munna.springboot.day17.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.springboot.day17.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
