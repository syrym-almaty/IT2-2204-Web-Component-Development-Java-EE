package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnrollmentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public void enrollStudentInCourse(UUID studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Проверка на количество курсов
        if (student.getCourses().size() >= 5) {
            throw new RuntimeException("Student cannot enroll in more than 5 courses");
        }

        // Проверка на количество студентов в курсе
        if (course.getStudents().size() >= 30) {
            throw new RuntimeException("Course cannot have more than 30 students");
        }

        // Добавление курса в список студентов и студента в список курсов
        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
        courseRepository.save(course); // Сохраняем изменения курса
    }
}
