package com.munna.sms.service;

import java.util.List;

import com.munna.sms.entity.Enrollment;

public interface EnrollmentService {
	
	Enrollment enrollStudent(Long studentId, Long courseId, String date);

    List<Enrollment> getAllEnrollments();

    void deleteEnrollment(Long enrollmentId);

}
