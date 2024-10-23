package com.example.demo.strategy;

public class LetterGradingStrategy implements GradingStrategy {
    @Override
    public Double calculateGrade(Double score) {
        // Convert numeric score to letter grade
        return score;
    }
}


