package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Course name is required")
    private String name;

    @NotBlank(message = "Course code is required")
    @Column(unique = true)
    private String code;

    @Setter
    @Getter
    @ManyToMany(mappedBy = "courses")
    @JsonIgnoreProperties("courses") // игнорируем студентов внутри курса
    private Set<Student> students = new HashSet<>();
    // Метод доступа к полю credits
    @Getter
    @NotNull
    private Integer credits;

    public @NotBlank(message = "Course name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Course name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Course code is required") String getCode() {
        return code;
    }

    public void setCode(@NotBlank(message = "Course code is required") String code) {
        this.code = code;
    }

    public void setCredits(@NotNull Integer credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", credits=" + credits +
                ", studentIds=" + students.stream().map(Student::getId).toList() +  // Выводим только ID студентов
                '}';
    }

}