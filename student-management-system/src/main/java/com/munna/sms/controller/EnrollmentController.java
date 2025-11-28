package com.munna.sms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.munna.sms.entity.Enrollment;
import com.munna.sms.service.EnrollmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
@Slf4j
public class EnrollmentController {
	
	private final EnrollmentService enrollmentService;

    @PostMapping("/{studentId}/{courseId}")
    public ResponseEntity<Enrollment> enrollStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId,
            @RequestParam String date) {

        log.info("API: Enroll student {} to course {} on {}", studentId, courseId, date);

        return ResponseEntity.ok(enrollmentService.enrollStudent(studentId, courseId, date));
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        log.info("API: Get All Enrollments");
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEnrollment(@PathVariable Long id) {
        log.info("API: Delete Enrollment {}", id);
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.ok("Enrollment deleted successfully");
    }

}
