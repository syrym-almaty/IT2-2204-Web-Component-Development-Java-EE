package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(generator = "UUID")

    @Column(columnDefinition = "uuid")
    @Schema(description = "UUID идентификатор студента", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;

    @Schema(description = "Имя студента", example = "string")
    private String name;

    @Schema(description = "Email студента", example = "string")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "enrollments",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnoreProperties("students") // игнорируем студентов внутри курса
    private Set<Course> courses = new HashSet<>();
    @ManyToOne
    private UserEntity user;
    private Double gpa;
    // Constructors
    public Student() {}

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }


    // Метод доступа к полю grades
    @Getter
    @OneToMany(mappedBy = "student")
    @JsonBackReference
    private Set<Grade> grades = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gpa=" + gpa +
                ", courseIds=" + courses.stream().map(Course::getId).toList() +  // Выводим только ID курсов
                '}';
    }

}
