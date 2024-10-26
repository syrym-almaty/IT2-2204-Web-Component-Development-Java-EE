package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Users implements UserDetails {
    @Id
    private String username;
    private String password;
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    // Methods from UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return the roles as GrantedAuthorities
        return roles.stream().collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Assuming no account expiration logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Assuming no account lock logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Assuming no credentials expiration logic
    }
}