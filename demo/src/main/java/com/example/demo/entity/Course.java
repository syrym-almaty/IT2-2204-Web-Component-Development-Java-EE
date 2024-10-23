package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @OneToMany(mappedBy = "course")
    private Set<Grade> grades;

    @Getter
    private Integer credits;

    @ManyToMany // Устанавливаем связь ManyToMany с студентами
    @JoinTable(
            name = "course_student", // Имя промежуточной таблицы
            joinColumns = @JoinColumn(name = "course_id"), // Внешний ключ для Course
            inverseJoinColumns = @JoinColumn(name = "student_id") // Внешний ключ для Student
    )
    private Set<Student> students; // Изменяем на Set<Student> для хранения студентов
}

