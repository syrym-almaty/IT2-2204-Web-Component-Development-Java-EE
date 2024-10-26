package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.exception.BusinessException;
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
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    // Обновление студента
    public Student updateStudent(UUID id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setEmail(updatedStudent.getEmail());
                    student.setGpa(updatedStudent.getGpa()); // Обновляем GPA, если это требуется
                    // Обновите другие поля по мере необходимости
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    // Удаление студента
    public void deleteStudent(UUID id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
        studentRepository.deleteById(id);
    }

    // Расчет GPA студента
    public Double calculateGPA(UUID studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + studentId));

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

        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }

    // Проверка на ограничение количества курсов при зачислении
    public void enrollStudentInCourse(Student student, Course course) {
        // Ограничение на 5 курсов для студента
        if (student.getCourses().size() >= 5) {
            throw new BusinessException("Cannot enroll in more than 5 courses.");
        }

        // Ограничение на 30 студентов для курса
        if (course.getStudents().size() >= 30) {
            throw new BusinessException("Course capacity reached.");
        }

        // Добавляем курс студенту
        student.getCourses().add(course);
        studentRepository.save(student);
    }

    // Проверка существования студента по ID
    public boolean existsById(UUID id) {
        return studentRepository.existsById(id);
    }
}
