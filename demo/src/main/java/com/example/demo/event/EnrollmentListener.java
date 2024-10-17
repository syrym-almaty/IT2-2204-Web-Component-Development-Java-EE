package com.example.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentListener {
    @EventListener
    public void handleEnrollmentEvent(EnrollmentEvent event) {
        // Логика обработки события, например, отправка уведомления по электронной почте
    }
}
