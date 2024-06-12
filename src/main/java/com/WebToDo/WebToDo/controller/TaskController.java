package com.WebToDo.WebToDo.controller;

import com.WebToDo.WebToDo.models.Task;
import com.WebToDo.WebToDo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getAllTasksForUser(userId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/user/{userId}/completed")
    public ResponseEntity<List<Task>> getAllCompletedTasks(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.findAllCompletedTasksForUser(userId));
    }

    @GetMapping("/user/{userId}/incomplete")
    public ResponseEntity<List<Task>> getAllIncompleteTasks(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.findAllIncompleteTasksForUser(userId));
    }

    @GetMapping("/user/{userId}/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long userId, @PathVariable Long id) {
        Task task = taskService.findTaskById(id);
        if (task != null && task.getAuthor().equals(userId)) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @PathVariable Long userId) {
        Task createdTask = taskService.createNewTask(task, userId);
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/user/{userId}/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long userId, @PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        Task updatedTask = taskService.updateTask(task, userId);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/user/{userId}/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long userId, @PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id, userId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
