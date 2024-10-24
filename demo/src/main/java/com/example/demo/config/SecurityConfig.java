package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Отключение CSRF для упрощения
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().authenticated() // Все запросы требуют аутентификации
                )
                .formLogin(login -> login // Включаем стандартную форму логина
                        .permitAll() // Доступ для всех к странице логина
                )
                .logout(logout -> logout // Включаем возможность выхода
                        .permitAll() // Разрешаем доступ ко всем для выхода
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // In-memory пользователи для примера
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("er")
                .password("1234")
                .roles("USER").build());
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Без шифрования паролей
    }
}