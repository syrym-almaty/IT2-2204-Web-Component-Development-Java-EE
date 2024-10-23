package com.example.demo.listener;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.event.EnrollmentEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentListener {

    @EventListener
    public void handleEnrollmentEvent(EnrollmentEvent event) {
        Student student = event.getStudent();
        Course course = event.getCourse();

        // Example: Send email notification (pseudo-code)
        System.out.println("Student " + student.getName() + " enrolled in " + course.getName());
        // Actual implementation can involve an EmailService to send notifications
    }
}
