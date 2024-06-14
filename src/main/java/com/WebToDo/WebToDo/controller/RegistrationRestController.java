package com.WebToDo.WebToDo.controller;

import com.WebToDo.WebToDo.dto.UserRegistrationDto;
import com.WebToDo.WebToDo.models.User;
import com.WebToDo.WebToDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/register")
public class RegistrationRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDto userDto) {
        System.out.println("Received registration request for user: " + userDto.getUsername());

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            System.out.println("Password mismatch for user: " + userDto.getUsername());
            return ResponseEntity.badRequest().body("password_mismatch");
        }

        if (userService.findByUsername(userDto.getUsername()).isPresent()) {
            System.out.println("Username already exists: " + userDto.getUsername());
            return ResponseEntity.badRequest().body("username");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole("USER");

        userService.save(user);
        System.out.println("User registered successfully: " + userDto.getUsername());
        return ResponseEntity.ok("success");
    }
}

