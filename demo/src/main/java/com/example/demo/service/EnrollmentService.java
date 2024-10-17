package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import exception.ResourceNotFoundException;

public void enrollStudentInCourse(Long studentId, Long courseId) {
    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

    Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

    if (student.getCourses().size() >= 5) {
        throw new BusinessException("Cannot enroll in more than 5 courses.");
    }

    if (course.getStudents().size() >= 30) {
        throw new BusinessException("Course capacity reached.");
    }

    student.getCourses().add(course);
    course.getStudents().add(student);

    studentRepository.save(student);
    courseRepository.save(course);
}
