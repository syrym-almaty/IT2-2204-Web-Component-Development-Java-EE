package com.example.demo.service;

import com.example.demo.DTO.RegistrationDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.Student;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;



    @Transactional
    public void registerNewUser(RegistrationDTO registrationDTO) {
        // Создаем нового студента
        Student newStudent = new Student();
        newStudent.setName(registrationDTO.getName());
        newStudent.setEmail(registrationDTO.getEmail());
        studentRepository.save(newStudent);

        // Создаем нового пользователя и связываем с только что созданным студентом
        UserEntity newUser = new UserEntity();
        newUser.setUsername(registrationDTO.getName());
        newUser.setPassword("{noop}" + registrationDTO.getPassword());
        newUser.setStudent(newStudent); // Связываем пользователя с новым студентом
        Role studentRole = roleRepository.findByName("STUDENT");
        newUser.setRoles(Collections.singleton(studentRole));
        userRepository.save(newUser);
    }
}
