package com.WebToDo.WebToDo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The task field cannot be empty")
    private String task;
    private Long author;
    private String Theme;
    private String Description;
    private LocalDate CreationDate;
    private LocalDate Deadline;
    private boolean completed;

    public Task() {
        this.task = task;
        this.completed = completed;
        this.CreationDate = LocalDate.now();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getCreationDate() {
        return CreationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.CreationDate = creationDate;
    }

    public Long getAuthor() {
        return author;
    }
    public void setAuthor(Long author) {
        this.author = author;
    }

    public String getTheme() {
        return Theme;
    }
    public void setTheme(String theme) {
        this.Theme = theme;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        this.Description = description;
    }

    public LocalDate getDeadline() {
        return Deadline;
    }
    public void setDeadline(LocalDate deadline) {
        this.Deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
