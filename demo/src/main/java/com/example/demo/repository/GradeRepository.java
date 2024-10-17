package com.example.demo.repository;

import com.example.demo.entity.Grade;
import com.example.demo.entity.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GradeRepository extends JpaRepository<Grade, GradeId> {
    List<Grade> findByStudentId(UUID studentId); // Метод для получения оценок по ID студента
}
