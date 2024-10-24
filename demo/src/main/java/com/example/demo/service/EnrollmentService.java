package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseRepository;
import com.example.demo.entity.Student;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EnrollmentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public void enrollStudentInCourse(UUID studentId, UUID courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // Business logic validations
        if (student.getCourses().size() >= 5) {
            throw new BusinessException("Cannot enroll in more than 5 courses.");
        }

        if (course.getStudents().size() >= 30) {
            throw new BusinessException("Course capacity reached.");
        }

        // Enroll student in course
        student.getCourses().add(course);
        course.getStudents().add(student);

        // Save student (saving course is not necessary)
        studentRepository.save(student);
    }
}
