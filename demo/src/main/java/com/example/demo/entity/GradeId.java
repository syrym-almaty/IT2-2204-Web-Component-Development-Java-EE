package com.example.demo.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.*;
import java.io.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeId implements Serializable {
    private Long studentId;
    private Long courseId;
}