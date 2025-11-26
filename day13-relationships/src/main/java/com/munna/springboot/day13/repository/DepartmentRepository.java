package com.munna.springboot.day13.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.munna.springboot.day13.entity.Department;


public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
