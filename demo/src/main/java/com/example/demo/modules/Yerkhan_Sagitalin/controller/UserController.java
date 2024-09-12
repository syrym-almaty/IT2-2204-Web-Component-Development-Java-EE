package com.example.demo.modules.Yerkhan_Sagitalin.controller;

import com.example.demo.modules.Abdikarimov_Aran.model.User;
import com.example.demo.modules.Abdikarimov_Aran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the controller class for managing Users.
 * It handles requests like viewing the list of users or adding new users.
 */
@Controller
@RequestMapping("/users") // This maps the base URL for this controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Displays all users on the user list page.
     * 
     * @param model The Model object is used to pass data to the view.
     * @return The name of the HTML file (user-list.html) to be rendered.
     */
    @GetMapping("/all")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users); // Adds the list of users to the model
        return "user-list"; // Refers to user-list.html in the resources folder
    }

    /**
     * Handles the addition of a new user.
     * 
     * @param user The user object coming from the form submission.
     * @return Redirects to the list of all users after adding.
     */
    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user); // Calls the service layer to save the user
        return "redirect:/users/all"; // Redirects to the updated list of users
    }
}
