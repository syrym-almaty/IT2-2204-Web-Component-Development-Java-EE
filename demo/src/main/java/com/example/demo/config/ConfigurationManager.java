package com.example.demo.config;

public class ConfigurationManager {
    private static ConfigurationManager instance;

    private ConfigurationManager() {
        // Загрузка конфигурации
    }

    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }
}
