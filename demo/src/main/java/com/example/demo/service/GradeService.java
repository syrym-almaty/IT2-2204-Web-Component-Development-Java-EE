package com.example.demo.service;

import com.example.demo.DTO.GradeDTO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Grade;
import com.example.demo.entity.Student;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.GradeRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.strategy.PercentageGradingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final PercentageGradingStrategy percentageGradingStrategy;

    public void addGrade(GradeDTO gradeDTO) {
        // Найдем студента и курс по их ID
        Student student = studentRepository.findById(gradeDTO.getId().getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Course course = courseRepository.findById(gradeDTO.getId().getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        // Создаем новую оценку
        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setScore(gradeDTO.getScore());

        // Сохраняем оценку
        gradeRepository.save(grade);

        // Пересчитываем GPA для студента
        Double newGpa = calculateGPA(student.getId());
        student.setGpa(newGpa);
        studentRepository.save(student);
    }

    public Double calculateGPA(UUID studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

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

        return percentageGradingStrategy.calculateGrade( totalPoints / totalCredits);
    }
}
