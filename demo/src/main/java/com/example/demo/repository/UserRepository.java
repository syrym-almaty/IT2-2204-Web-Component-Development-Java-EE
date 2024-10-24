package com.example.demo.repository;

import com.example.demo.entity.User; // Убедитесь, что User существует
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
