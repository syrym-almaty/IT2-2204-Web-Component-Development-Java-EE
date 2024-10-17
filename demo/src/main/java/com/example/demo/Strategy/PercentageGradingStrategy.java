package com.example.demo.Strategy;

public class PercentageGradingStrategy implements GradingStrategy {
    @Override
    public Double calculateGrade(Double score) {
        // Assuming score is out of 100, convert to a 4.0 scale
        if (score >= 90) return 4.0; // A
        if (score >= 80) return 3.0; // B
        if (score >= 70) return 2.0; // C
        if (score >= 60) return 1.0; // D
        return 0.0; // F
    }
}
