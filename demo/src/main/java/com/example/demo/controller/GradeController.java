package com.example.demo.controller;

import com.example.demo.DTO.GradeDTO;
import com.example.demo.service.GradeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grades")
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")  // Applies JWT security to this controller
public class GradeController {

    private final GradeService gradeService;


    @PostMapping
    public ResponseEntity<String> addGrade(@RequestBody GradeDTO gradeDTO) {
        try {
            gradeService.addGrade(gradeDTO);
            return ResponseEntity.ok("Grade added successfully and GPA updated.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }
}
