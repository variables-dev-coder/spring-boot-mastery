package com.munna.sms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.munna.sms.entity.Student;
import com.munna.sms.repository.StudentRepository;
import com.munna.sms.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
	
	private final StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        log.info("Creating student: {}", student.getName());
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        log.info("Fetching student by id: {}", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student Not Found: " + id));
    }

    @Override
    public List<Student> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        log.info("Updating student with id: {}", id);

        Student student = getStudentById(id);
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());

        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Deleting student with id: {}", id);
        studentRepository.deleteById(id);
    }

}
