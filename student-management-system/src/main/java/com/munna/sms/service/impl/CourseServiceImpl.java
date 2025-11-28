package com.munna.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.munna.sms.entity.Course;
import com.munna.sms.repository.CourseRepository;
import com.munna.sms.service.CourseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
	
	private final CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        log.info("Creating course: {}", course.getTitle());
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        log.info("Fetching course by id: {}", id);
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course Not Found: " + id));
    }

    @Override
    public List<Course> getAllCourses() {
        log.info("Fetching all courses");
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Long id, Course updatedCourse) {
        log.info("Updating course with id: {}", id);

        Course course = getCourseById(id);
        course.setTitle(updatedCourse.getTitle());
        course.setDescription(updatedCourse.getDescription());

        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        log.info("Deleting course with id: {}", id);
        courseRepository.deleteById(id);
    }

}
