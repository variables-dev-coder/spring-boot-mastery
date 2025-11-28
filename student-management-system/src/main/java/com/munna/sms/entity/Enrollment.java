package com.munna.sms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Student → Many enrollments
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // Course → Many enrollments
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String enrollmentDate;

}
