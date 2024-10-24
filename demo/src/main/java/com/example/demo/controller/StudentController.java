package com.example.demo.controller;

import com.example.demo.DTO.StudentDTO;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Controller", description = "CRUD operations for Students")
@SecurityRequirement(name = "bearerAuth")  // Applies JWT security to this controller
public class StudentController {

    private StudentService studentService;
    private ModelMapper modelMapper;

    public StudentController(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get All Students", description = "Retrieve a list of all students")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "Create Student", description = "Create a new student")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Student created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public Student createStudent(
            @RequestBody StudentDTO studentDTO) {
        return studentService.createStudent(convertToEntity(studentDTO));
    }
    @PutMapping("/{studentId}/courses")
    public ResponseEntity<String> addCoursesToStudent(
            @PathVariable UUID studentId,
            @RequestBody List<UUID> courseIds) {
        System.out.println(courseIds);
        return studentService.addCoursesToStudent(studentId, courseIds);
    }


    @Operation(summary = "Get Student by ID", description = "Retrieve a student by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved student"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/{id}")
    public Student getStudentById(
            @Parameter(description = "UUID of the student to retrieve", required = true)
            @PathVariable UUID id) {
        return studentService.getStudentById(id);
    }

    @Operation(summary = "Delete Student", description = "Delete a student by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/{id}")
    public void deleteStudent(
            @Parameter(description = "UUID of the student to delete", required = true)
            @PathVariable UUID id) {
        studentService.deleteStudent(id);
    }
    @Operation(summary = "Update Student", description = "Update an existing student's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @PutMapping("/{id}")
    public Student updateStudent(
            @Parameter(description = "UUID of the student to update", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Updated student object", required = true)
            @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }
    public Student convertToEntity(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, Student.class);
    }

    public StudentDTO convertToDTO(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }
}
