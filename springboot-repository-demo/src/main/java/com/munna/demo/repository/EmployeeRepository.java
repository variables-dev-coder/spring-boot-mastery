package com.munna.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.munna.demo.projection.EmployeeSummary;
import com.munna.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Basic derived query
    List<Employee> findByDepartment(String department);

    // Salary greater than
    List<Employee> findBySalaryGreaterThan(double salary);

    // Pagination example
    Page<Employee> findByActiveTrue(Pageable pageable);
    
    @Query("SELECT e FROM Employee e JOIN FETCH e.department")
    List<Employee> findAllWithDepartment();

    // Projection method (IMPORTANT ONE)
    List<EmployeeSummary> findByDepartmentAndActiveTrueOrderBySalaryDesc(String department);
}