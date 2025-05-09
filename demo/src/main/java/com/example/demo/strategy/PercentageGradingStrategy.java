package com.example.demo.strategy;

public class PercentageGradingStrategy implements GradingStrategy {
    @Override
    public Double calculateGrade(Double score) {
        // Логика преобразования числовой оценки в процентную
        return score; // Временно возвращаем score для примера
    }
}
