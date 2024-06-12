package com.WebToDo.WebToDo.service;

import com.WebToDo.WebToDo.models.Task;
import com.WebToDo.WebToDo.models.User;
import com.WebToDo.WebToDo.repositories.TaskRepository;
import com.WebToDo.WebToDo.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasksForUser(Long userId) {
        return taskRepository.findByAuthorId(userId);
    }

    public List<Task> findAllCompletedTasksForUser(Long userId) {
        return taskRepository.findCompletedTasksByAuthorId(userId);
    }

    public List<Task> findAllIncompleteTasksForUser(Long userId) {
        return taskRepository.findIncompleteTasksByAuthorId(userId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task createNewTask(@NotNull Task task, Long userId) {
        task.setAuthor(userId);
        return taskRepository.save(task);
    }

    public Task updateTask(Task task, Long userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Task existingTask = taskRepository.findById(task.getId()).orElse(null);
        if (existingTask != null && (existingTask.getAuthor().equals(userId) || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN")))) {
            task.setAuthor(existingTask.getAuthor());
            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    public boolean deleteTask(Long taskId, Long userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Task existingTask = taskRepository.findById(taskId).orElse(null);
        if (existingTask != null && (existingTask.getAuthor().equals(userId) || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN")))) {
            taskRepository.deleteById(taskId);
            return true;
        } else {
            return false;
        }
    }
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsersWithTasks() {
        List<Long> userIdsWithTasks = taskRepository.findAll()
                .stream()
                .map(Task::getAuthor)
                .distinct()
                .collect(Collectors.toList());

        return userRepository.findAllById(userIdsWithTasks);
    }
}
