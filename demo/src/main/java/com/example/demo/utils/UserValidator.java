package com.example.demo.utils;


import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@AllArgsConstructor
public class UserValidator implements Validator {
    private final UserDetailsService comicsUserDetailsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserEntity user = (UserEntity) target;
        try {
            comicsUserDetailsService.loadUserByUsername(user.getUsername());
        }catch (UsernameNotFoundException e) {
            return; // all ok
        }
        errors.rejectValue("username", "", "Username already exist");
    }
}
