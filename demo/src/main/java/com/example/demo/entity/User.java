package com.example.demo.entity;

import jakarta.persistence.FetchType;

@Entity
public class User implements UserDetails {
    @Id
    private String username;
    private String password;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    // Implement methods from UserDetails interface
}