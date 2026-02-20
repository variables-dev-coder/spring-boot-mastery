package com.munna.demo.repository;

import com.munna.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	
}