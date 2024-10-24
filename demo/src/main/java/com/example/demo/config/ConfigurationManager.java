package com.example.demo.config;

public class  ConfigurationManager {
    private static ConfigurationManager instance;

    private ConfigurationManager() {
        // Load configuration
    }

    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }
}