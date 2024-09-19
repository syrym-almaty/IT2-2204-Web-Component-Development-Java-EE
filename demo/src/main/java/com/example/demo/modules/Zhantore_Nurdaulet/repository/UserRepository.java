package com.example.demo.modules.Zhantore_Nurdaulet.repository;

import com.example.demo.modules.Abdikarimov_Aran.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * UserRepository simulates database operations for the User entity.
 * In a real-world application, this would connect to a database (e.g., using JPA).
 */
@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    /**
     * Returns all users.
     */
    public List<User> findAll() {
        return users;
    }

    /**
     * Saves a user to the in-memory list.
     */
    public void save(User user) {
        users.add(user);
    }
}
