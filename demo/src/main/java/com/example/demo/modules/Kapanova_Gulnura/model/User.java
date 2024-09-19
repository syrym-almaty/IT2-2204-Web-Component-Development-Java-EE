package com.example.demo.modules.Kapanova_Gulnura.model;

/**
 * The User class represents a user in the system.
 * It includes basic attributes like name, email, and ID.
 */
public class User {

    private Long id;
    private String name;
    private String email;

    // Constructor
    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
