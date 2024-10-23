package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeValidator implements ConstraintValidator<ValidCourseCode, String> {
    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        // Логика валидации: код должен состоять из 4 заглавных букв, затем 4 цифры
        return code != null && code.matches("^[A-Z]{4}\\d{4}$");
    }
}
