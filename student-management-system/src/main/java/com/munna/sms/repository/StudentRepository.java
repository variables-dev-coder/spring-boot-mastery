package com.munna.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.sms.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	// Custom query example (optional)
    Student findByEmail(String email);
}
