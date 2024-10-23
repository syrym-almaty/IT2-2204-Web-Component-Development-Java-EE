package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
@PreAuthorize("hasRole('STUDENT')") // Ограничение доступа для студентов
public class StudentController {

    @GetMapping("/dashboard")
    public String getStudentDashboard() {
        return "Welcome to the Student Dashboard!";
    }

    // Другие студенческие эндпоинты
}
