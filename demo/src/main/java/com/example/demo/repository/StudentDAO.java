package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public class StudentDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public void save(Student student) {
        entityManager.persist(student);
    }

    // Other data access methods...
}