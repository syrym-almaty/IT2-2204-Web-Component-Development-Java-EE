package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebFluxConfig {

    @Value("${webflux.server.port:8081}")
    private int webfluxPort;

    @Bean
    public WebServerFactoryCustomizer<NettyReactiveWebServerFactory> webfluxServer() {
        return factory -> factory.setPort(webfluxPort); // Настройка порта для Netty
    }
}
