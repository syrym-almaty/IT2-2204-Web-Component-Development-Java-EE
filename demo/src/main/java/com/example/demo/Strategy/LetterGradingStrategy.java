package com.example.demo.Strategy;

import com.example.demo.Strategy.GradingStrategy;

public class LetterGradingStrategy implements GradingStrategy {
    @Override
    public Double calculateGrade(Double score) {
        // Convert numeric score to letter grade, e.g.:
        if (score >= 90) return 4.0; // A
        if (score >= 80) return 3.0; // B
        if (score >= 70) return 2.0; // C
        if (score >= 60) return 1.0; // D
        return 0.0; // F
    }
}
