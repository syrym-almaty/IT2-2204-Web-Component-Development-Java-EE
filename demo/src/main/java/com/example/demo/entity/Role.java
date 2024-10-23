package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;  // Не забудьте добавить, если необходимо
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")  // Добавьте имя таблицы, если нужно
public class Role implements GrantedAuthority {
    @Id
    private String name;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
