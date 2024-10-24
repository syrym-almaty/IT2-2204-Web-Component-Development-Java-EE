package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Role implements GrantedAuthority {
    @Id
    private String name;
    @ManyToMany
    @JoinTable(
            name = "user_entity_role",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnoreProperties("user_entity") // игнорируем студентов внутри курса
    private Set<UserEntity> userEntities = new HashSet<>();
    @Override
    public String getAuthority() {
        return "ROLE_" + name;
    }

    // Implement methods from GrantedAuthority interface
}
