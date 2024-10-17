package com.example.demo.service;

import java.util.List;
import java.util.UUID;

import com.example.demo.entity.Student;
import com.example.demo.entity.Grade; // Import the Grade entity
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.GradeRepository; // Import the Grade repository

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository; // Inject the GradeRepository

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

    // Method to calculate GPA
    public Double calculateGPA(UUID studentId) {
        List<Grade> grades = gradeRepository.findByStudentId(studentId); // Fetch grades for the student

        if (grades.isEmpty()) {
            return 0.0; // No grades available, return GPA as 0.0
        }

        // Calculate GPA as the average of all grades
        double totalScore = grades.stream().mapToDouble(Grade::getScore).sum();
        return totalScore / grades.size(); // Return the average score as GPA
    }
}
