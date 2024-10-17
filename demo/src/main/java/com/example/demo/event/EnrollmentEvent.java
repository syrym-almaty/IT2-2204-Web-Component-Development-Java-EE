package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

public class EnrollmentEvent extends ApplicationEvent {
    private final Student student;
    private final Course course;

    public EnrollmentEvent(Object source, Student student, Course course) {
        super(source);
        this.student = student;
        this.course = course;
    }

    // Getters
    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }
}
