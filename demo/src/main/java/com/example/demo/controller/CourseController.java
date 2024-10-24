package com.example.demo.controller;

import com.example.demo.DTO.CourseDTO;
import com.example.demo.DTO.StudentDTO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/courses")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Course Controller", description = "CRUD operations for Courses")
public class CourseController {
    CourseService courseService;
    ModelMapper modelMapper;

    @Operation(summary = "Get All Students", description = "Retrieve a list of all students")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping
    public List<Course> getAllCourses() {

        return courseService.getAllCourses();
    }
    @GetMapping("/{id}")
    public Course getCourseById(
            @Parameter(description = "UUID of the student to retrieve", required = true)
            @PathVariable UUID id) {
        return courseService.getCourseById(id);
    }
    @PostMapping
    public Course createCourse(
            @RequestBody CourseDTO courseDTO) {
        return courseService.addCourse(convertToEntity(courseDTO));
    }
    public Course convertToEntity(CourseDTO courseDTO) {
        return modelMapper.map(courseDTO, Course.class);
    }

    public CourseDTO convertToCourse(Course course) {
        return modelMapper.map(course, CourseDTO.class);
    }
}
