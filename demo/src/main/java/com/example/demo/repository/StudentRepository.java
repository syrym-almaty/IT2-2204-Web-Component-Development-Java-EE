package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.*;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
}
