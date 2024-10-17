package com.example.demo.service;

import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.repository.GradeRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository; // Исправлено

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

    public Double calculateGPA(UUID studentId) {
        // Получаем все оценки студента
        List<Grade> grades = gradeRepository.findByStudentId(studentId);

        if (grades.isEmpty()) {
            return 0.0; // Если нет оценок, возвращаем 0
        }

        double totalPoints = 0.0;
        int totalCredits = 0;

        // Подсчитываем общую сумму баллов и кредитов
        for (Grade grade : grades) {
            int credits = grade.getCourse().getCredits(); // Предполагаем, что у курса есть поле credits
            totalPoints += grade.getScore() * credits; // Умножаем оценку на количество кредитов
            totalCredits += credits;
        }

        return totalPoints / totalCredits; // Возвращаем среднее значение GPA
    }
}
