package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CourseService {
    CourseRepository courseRepository;
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public Course getCourseById(UUID id) {
        return courseRepository.findById(id).orElse(null);
    }
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }
}
