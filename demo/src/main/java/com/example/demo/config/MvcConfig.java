package com.example.demo.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MvcConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> mvcServer() {
        return factory -> factory.setPort(8080); // Настройка порта для Tomcat
    }
}
