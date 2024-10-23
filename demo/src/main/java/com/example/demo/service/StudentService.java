package com.example.demo.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.example.demo.entity.Student;
import com.example.demo.entity.Grade;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.GradeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(UUID id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(UUID id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setEmail(updatedStudent.getEmail());
                    // Add other fields as necessary
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    // Method to calculate GPA as a weighted average
    public Double calculateGPA(UUID studentId) {
        // Fetch the student by ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        // Get all grades for the student
        Set<Grade> grades = student.getGrades();
        if (grades.isEmpty()) {
            return 0.0; // No grades available, return GPA as 0.0
        }

        // Calculate weighted GPA
        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Grade grade : grades) {
            int credits = grade.getCourse().getCredits(); // Get the credits for the course
            totalPoints += grade.getScore() * credits; // Weight the score by the credits
            totalCredits += credits; // Sum up the total credits
        }

        return totalPoints / totalCredits; // Return the weighted GPA
    }
}
