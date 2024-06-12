package com.WebToDo.WebToDo.controller;

import com.WebToDo.WebToDo.models.Task;
import com.WebToDo.WebToDo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usertasks")
public class UserTaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/user/{userId}")
    public List<Task> getUserTasks(@PathVariable Long userId) {
        return taskRepository.findByAuthor(userId);
    }
}
