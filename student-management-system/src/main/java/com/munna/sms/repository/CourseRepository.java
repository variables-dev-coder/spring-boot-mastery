package com.munna.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.sms.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	

}
