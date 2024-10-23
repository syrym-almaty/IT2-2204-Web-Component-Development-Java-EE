package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.entity.Course;
import com.example.demo.entity.Grade;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JpaRepository<Course, Long> courseRepository; // Используем JpaRepository напрямую

    // Метод для получения всех студентов
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Метод для создания нового студента
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Метод для получения студента по ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    // Метод для обновления данных студента
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = getStudentById(id);
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setAge(updatedStudent.getAge());
        return studentRepository.save(existingStudent);
    }

    // Метод для удаления студента
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    // Метод для расчета GPA студента
    public Double calculateGPA(Long studentId) {
        Student student = getStudentById(studentId);
        var grades = student.getGrades();

        if (grades.isEmpty()) {
            return 0.0;
        }

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (var grade : grades) {
            int credits = grade.getCourse().getCredits();
            totalPoints += grade.getScore() * credits;
            totalCredits += credits;
        }

        return totalPoints / totalCredits;
    }

    // Метод для регистрации студента на курс
    public void enrollStudent(Long studentId, Long courseId) {
        Student student = getStudentById(studentId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

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
}
