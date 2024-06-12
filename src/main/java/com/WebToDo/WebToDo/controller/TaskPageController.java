package com.WebToDo.WebToDo.controller;

import com.WebToDo.WebToDo.models.User;
import com.WebToDo.WebToDo.repositories.UserRepository;
import com.WebToDo.WebToDo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TaskPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public String showTasksPage(Model model) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("userId", currentUser.getId());
        model.addAttribute("userRole", currentUser.getRole());

        if ("ADMIN".equals(currentUser.getRole())) {
            List<User> usersWithTasks = taskService.getAllUsersWithTasks();
            model.addAttribute("usersWithTasks", usersWithTasks);
        }

        return "tasks";
    }
}
