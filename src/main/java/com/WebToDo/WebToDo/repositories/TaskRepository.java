package com.WebToDo.WebToDo.repositories;

import com.WebToDo.WebToDo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByTask(String task);
    List<Task> findByCompletedTrue();
    List<Task> findByCompletedFalse();
    List<Task> findAll();
    Task getById(Long id);

    List<Task> findByAuthor(Long author);

    @Query("SELECT t FROM Task t WHERE t.author = ?1")
    List<Task> findByAuthorId(Long authorId);

    @Query("SELECT t FROM Task t WHERE t.author = ?1 AND t.completed = true")
    List<Task> findCompletedTasksByAuthorId(Long authorId);

    @Query("SELECT t FROM Task t WHERE t.author = ?1 AND t.completed = false")
    List<Task> findIncompleteTasksByAuthorId(Long authorId);
}
