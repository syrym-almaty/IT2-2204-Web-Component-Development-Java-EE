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
public class Grade {
    @EmbeddedId
    private GradeId id = new GradeId();

    @ManyToOne
    @MapsId("studentId")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    @NotNull
    private Double score;
}