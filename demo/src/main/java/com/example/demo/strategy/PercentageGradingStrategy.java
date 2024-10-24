package com.example.demo.strategy;

public class PercentageGradingStrategy implements GradingStrategy {
    @Override
    public Double calculateGrade(Double score) {
        return score; // Simply returns the score as the grade
    }
}
