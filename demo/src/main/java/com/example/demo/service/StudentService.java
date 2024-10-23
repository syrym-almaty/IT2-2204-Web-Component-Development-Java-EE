package com.example.demo.service;

import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import exception.ResourceNotFoundException;  // Обратите внимание на корректные импорты
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;  // Используется для идентификаторов

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Метод для получения всех студентов
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Метод для создания нового студента
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Метод для получения студента по UUID
    public Student getStudentById(UUID id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    // Метод для удаления студента по UUID
    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    // Метод для расчета GPA студента
    public Double calculateGPA(UUID studentId) {
        // Получаем студента по UUID
        Student student = getStudentById(studentId);

        // Получаем список оценок студента
        Set<Grade> grades = student.getGrades();

        // Если нет оценок, возвращаем GPA 0.0
        if (grades.isEmpty()) {
            return 0.0;
        }

        // Переменные для общего количества баллов и кредитов
        double totalPoints = 0.0;
        int totalCredits = 0;

        // Расчет общего количества баллов и кредитов
        for (Grade grade : grades) {
            int credits = grade.getCourse().getCredits();  // Получаем количество кредитов за курс
            totalPoints += grade.getScore() * credits;  // Умножаем оценку на кредиты
            totalCredits += credits;  // Суммируем кредиты
        }

        // Возвращаем рассчитанный GPA
        return totalPoints / totalCredits;
    }
}
