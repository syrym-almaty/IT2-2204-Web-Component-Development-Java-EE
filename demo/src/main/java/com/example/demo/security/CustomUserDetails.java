package com.example.demo.security;

import com.example.demo.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final UserEntity user;

    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))  // Добавляем префикс ROLE_
                .collect(Collectors.toList());

        // Example: Adding custom logic based on student GPA
        if (user.getStudent() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
        }

        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Здесь можно добавить свою логику
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Здесь можно добавить свою логику
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Здесь можно добавить свою логику
    }

    @Override
    public boolean isEnabled() {
        return true; // Здесь можно добавить свою логику
    }
}
