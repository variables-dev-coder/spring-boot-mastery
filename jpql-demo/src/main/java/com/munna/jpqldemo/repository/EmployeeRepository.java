package com.munna.jpqldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.munna.jpqldemo.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	
	//===========JPQL: Find By Department===========
	
	@Query("SELECT e FROM Employee e WHERE e.department = :dept")
	List<Employee> findByDepartment(@Param("dept") String department);
	
	
	//===========JPQL: Salary Range (BETWEEN)===========
	
	@Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :min AND :max")
	List<Employee> findBySalaryRange(@Param("min") double min,
	                                 @Param("max") double max);
	
	
	//============JPQL: Aggregation (COUNT)=================
	
	@Query("SELECT COUNT(e) FROM Employee e WHERE e.department = :dept")
	Long countByDepartment(@Param("dept") String department);
	
	
	//==============JPQL Update Query====================
	
	@Modifying
	@Transactional
	@Query("UPDATE Employee e SET e.salary = :salary WHERE e.id = :id")
	int updateSalary(@Param("id") Long id,
	                 @Param("salary") double salary);
	

}