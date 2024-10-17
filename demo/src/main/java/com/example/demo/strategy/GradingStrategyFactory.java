package com.example.demo.strategy;

public class GradingStrategyFactory {
    public static GradingStrategy getStrategy(String type) {
        if (type.equalsIgnoreCase("letter")) {
            return new LetterGradingStrategy();
        }
        // Добавьте другие стратегии по мере необходимости
        return null;
    }
}
