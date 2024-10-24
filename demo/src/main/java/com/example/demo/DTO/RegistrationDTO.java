package com.example.demo.DTO;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegistrationDTO {
    private String name;
    private String password;
    @Email
    private String email;
    // Getters and setters
}
