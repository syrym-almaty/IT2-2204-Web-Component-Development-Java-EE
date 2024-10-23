package com.example.demo.service;

import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(UUID id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    public Double calculateGPA(UUID studentId) {
        Student student = getStudentById(studentId);
        Set<Grade> grades = student.getGrades();
        if (grades.isEmpty()) {
            return 0.0;
        }
        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Grade grade : grades) {
            int credits = grade.getCourse().getCredits();
            totalPoints += grade.getScore() * credits;
            totalCredits += credits;
        }

        return totalPoints / totalCredits;
    }
}
