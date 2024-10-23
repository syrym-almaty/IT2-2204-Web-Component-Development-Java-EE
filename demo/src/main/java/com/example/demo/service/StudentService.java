package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.entity.Grade;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Получение всех студентов
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Создание нового студента
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Получение студента по ID
    public Student getStudentById(UUID id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Удаление студента по ID
    public void deleteStudent(UUID id) {
        studentRepository.deleteById(id);
    }

    // Обновление информации о студенте
    public Student updateStudent(UUID id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setEmail(updatedStudent.getEmail());
                    // Добавьте другие поля по необходимости
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    // Метод для расчета GPA студента
    public Double calculateGPA(UUID studentId) {
        // Ищем студента по ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));

        // Получаем все оценки студента
        Set<Grade> grades = student.getGrades();
        if (grades.isEmpty()) {
            return 0.0;  // Если оценок нет, возвращаем 0.0
        }

        double totalPoints = 0.0;
        int totalCredits = 0;

        // Рассчитываем общие баллы и кредиты
        for (Grade grade : grades) {
            int credits = grade.getCourse().getCredits();  // Кредиты курса
            totalPoints += grade.getScore() * credits;     // Баллы умножаем на кредиты
            totalCredits += credits;                       // Суммируем кредиты
        }

        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;  // Средний балл
    }
}
