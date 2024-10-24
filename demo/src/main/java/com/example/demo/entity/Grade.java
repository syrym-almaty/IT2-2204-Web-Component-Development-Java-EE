package com.example.demo.entity;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.validation.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.UUID;
import java.util.*;
import java.io.*;

@Entity
@Table(name = "grades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Grade {
    @EmbeddedId
    private GradeID id = new GradeID();

    @ManyToOne
    @MapsId("studentId")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    @NotNull
    private Double score;

}