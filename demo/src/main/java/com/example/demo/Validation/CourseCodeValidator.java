package com.example.demo.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeValidator implements ConstraintValidator<ValidCourseCode, String> {

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        // Implement validation logic (e.g., check format)
        return code != null && code.matches("^[A-Z]{4}\\d{4}$");
    }
}
