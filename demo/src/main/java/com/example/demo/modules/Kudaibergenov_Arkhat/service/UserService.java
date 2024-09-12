package com.example.demo.modules.Kudaibergenov_Arkhat.service;

import com.example.demo.modules.Abdikarimov_Aran.model.User;
import com.example.demo.modules.Abdikarimov_Aran.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService handles the business logic for users.
 * It interacts with the UserRepository to perform CRUD operations.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves a list of all users.
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Adds a new user to the system.
     */
    public void addUser(User user) {
        userRepository.save(user);
    }
}
