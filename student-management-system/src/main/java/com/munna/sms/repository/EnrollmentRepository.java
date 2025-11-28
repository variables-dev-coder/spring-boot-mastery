package com.munna.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.sms.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
