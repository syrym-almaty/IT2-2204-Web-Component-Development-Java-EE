package com.example.demo.strategy;

public interface GradingStrategy {
    Double calculateGrade(Double score);
}

public class GradingStrategyFactory {
    public static GradingStrategy getStrategy(String type) {
        if (type.equalsIgnoreCase("letter")) {
            return new LetterGradingStrategy();
        } else if (type.equalsIgnoreCase("percentage")) {
            return new PercentageGradingStrategy();
        }
        return null;
    }
}