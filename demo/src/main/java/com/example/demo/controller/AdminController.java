package com.example.demo.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name = "bearerAuth")  // Applies JWT security to this controller
public class AdminController {
    // Admin-only endpoints
    @GetMapping("/helloAdmin")
    public String helloWorld() {
        return "Hello, Admin!";
    }
}
