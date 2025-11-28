package com.munna.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.munna.sms.entity.Course;
import com.munna.sms.entity.Enrollment;
import com.munna.sms.entity.Student;
import com.munna.sms.repository.CourseRepository;
import com.munna.sms.repository.EnrollmentRepository;
import com.munna.sms.repository.StudentRepository;
import com.munna.sms.service.EnrollmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {
	
	private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public Enrollment enrollStudent(Long studentId, Long courseId, String date) {
        log.info("Enrolling student {} into course {} on {}", studentId, courseId, date);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student Not Found: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course Not Found: " + courseId));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(date);

        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        log.info("Fetching all enrollments");
        return enrollmentRepository.findAll();
    }

    @Override
    public void deleteEnrollment(Long id) {
        log.info("Deleting enrollment with id: {}", id);
        enrollmentRepository.deleteById(id);
    }

}
