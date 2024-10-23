package com.example.demo.service;

import com.example.demo.entity.Grade;
import com.example.demo.strategy.GradingStrategy;
import com.example.demo.strategy.GradingStrategyFactory;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    public void applyGradingStrategy(Grade grade, String strategyType) {
        GradingStrategy strategy = GradingStrategyFactory.getStrategy(strategyType);
        if (strategy != null) {
            Double calculatedGrade = strategy.calculateGrade(grade.getScore());
            grade.setCalculatedGrade(calculatedGrade); // Теперь это должно работать
        }
    }
}
