package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Controller", description = "CRUD operations for Students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "Get All Students", description = "Retrieve a list of all students")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @PreAuthorize("hasRole('STUDENT')")  // Ограничение доступа для роли STUDENT
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "Create Student", description = "Create a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PreAuthorize("hasRole('ADMIN')")  // Только админ может создавать студентов
    @PostMapping
    public Student createStudent(
            @Parameter(description = "Student object to be created", required = true)
            @RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @Operation(summary = "Get Student by ID", description = "Retrieve a student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved student"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PreAuthorize("hasRole('STUDENT')")  // Студент может просматривать свои данные
    @GetMapping("/{id}")
    public Student getStudentById(
            @Parameter(description = "ID of the student to retrieve", required = true)
            @PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @Operation(summary = "Update Student", description = "Update an existing student's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PreAuthorize("hasRole('ADMIN')")  // Только админ может обновлять информацию
    @PutMapping("/{id}")
    public Student updateStudent(
            @Parameter(description = "ID of the student to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated student object", required = true)
            @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

    @Operation(summary = "Delete Student", description = "Delete a student by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PreAuthorize("hasRole('ADMIN')")  // Только админ может удалять студентов
    @DeleteMapping("/{id}")
    public void deleteStudent(
            @Parameter(description = "ID of the student to delete", required = true)
            @PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
