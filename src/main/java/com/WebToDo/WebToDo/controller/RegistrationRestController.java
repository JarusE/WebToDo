package com.WebToDo.WebToDo.controller;

import com.WebToDo.WebToDo.models.User;
import com.WebToDo.WebToDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegistrationRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public String registerUser(@RequestBody User user) {
        System.out.println("Received user: " + user.getUsername());

        if (userService.findByUsername(user.getUsername()).isPresent()) {
            System.out.println("Username already exists");
            return "username";
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            System.out.println("Password is empty");
            return "password";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userService.save(user);
        System.out.println("User registered successfully");
        return "success";
    }
}
