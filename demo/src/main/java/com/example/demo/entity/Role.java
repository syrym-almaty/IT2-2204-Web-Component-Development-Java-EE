package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
    @Id
    private String name;

    // Геттер для имени роли
    public String getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
