package com.example.demo.login;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/login", "/css/**", "/js/**").permitAll()  // Разрешаем доступ к странице логина и статике
                                .anyRequest().authenticated()  // Защищаем остальные страницы
                )
                .formLogin(formLogin ->  // Включаем форму логина
                        formLogin
                                .loginPage("/login")  // Указываем кастомную страницу входа
                                .permitAll()
                )
                .oauth2Login(oauth2Login ->  // Включаем OAuth2 (GitHub)
                        oauth2Login
                                .loginPage("/login")  // Используем ту же страницу входа
                );

        return http.build();
    }

    // Создаем пользователей для простой аутентификации (через форму)
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}