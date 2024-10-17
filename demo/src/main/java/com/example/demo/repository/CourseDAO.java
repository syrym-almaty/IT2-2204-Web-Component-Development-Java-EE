package com.example.demo.repository;

import com.example.demo.entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class CourseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    // Additional methods as necessary (update, delete, etc.)
}
