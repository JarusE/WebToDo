package com.WebToDo.WebToDo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegistrationDto {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 10, message = "Username must be at least 10 characters")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,}$", message = "The password must contain at least 8 characters, one uppercase letter, one lowercase letter and one special character")
    private String password;
    @NotBlank(message = "Confirm password cannot be empty")
    private String confirmPassword;

    @Email(message = "Incorrect email")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    // Getters and Setters
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
