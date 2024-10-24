package com.example.demo.DTO;

import java.util.UUID;

public class StudentDTO {
    private UUID id;
    private String name;
    private String email;

    public StudentDTO(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    // Геттеры и сеттеры
}
