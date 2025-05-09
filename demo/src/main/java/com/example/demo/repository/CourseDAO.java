package com.example.demo.repository;

import com.example.demo.entity.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    public void save(Course course) {
        entityManager.persist(course);
    }

    // Другие методы доступа к данным...
}
