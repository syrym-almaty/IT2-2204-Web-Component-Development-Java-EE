package com.example.demo.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeId implements Serializable {
    private UUID studentId;  // Changed to UUID to match Student entity
    private Long courseId;

    // Override equals and hashCode for proper functioning of the composite key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GradeId gradeId = (GradeId) o;

        if (!studentId.equals(gradeId.studentId)) return false;
        return courseId.equals(gradeId.courseId);
    }

    @Override
    public int hashCode() {
        int result = studentId.hashCode();
        result = 31 * result + courseId.hashCode();
        return result;
    }
}
