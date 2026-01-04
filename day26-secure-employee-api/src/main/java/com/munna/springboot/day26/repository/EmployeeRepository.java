package com.munna.springboot.day26.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.springboot.day26.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	

}
