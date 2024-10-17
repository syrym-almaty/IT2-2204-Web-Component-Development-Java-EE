package com.example.demo.strategy;

public class LetterGradingStrategy implements GradingStrategy {
    @Override
    public Double calculateGrade(Double score) {
        // Логика преобразования числовой оценки в буквенную
        // Например, преобразуйте числовую оценку в диапазон A, B, C и т.д.
        // Верните оценку в числовом формате
        return score; // Временно возвращаем score для примера
    }
}
