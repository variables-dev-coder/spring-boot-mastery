package com.munna.springboot.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.springboot.ems.model.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
