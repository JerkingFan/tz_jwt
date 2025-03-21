package org.example.tz_jwt.service;

import org.example.tz_jwt.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TaskService {
    Task createTask(Task task); // Создание задачи
    Task updateTask(Long id, Task task); // Редактирование задачи
    void deleteTask(Long id); // Удаление задачи
    Task changeTaskStatus(Long id, String status); // Изменение статуса задачи
    Task changeTaskPriority(Long id, String priority); // Изменение приоритета задачи
    Task assignTaskToUser(Long taskId, Long userId); // Назначение исполнителя задачи
    Page<Task> getTasksByAuthor(Long authorId, Pageable pageable);
    Page<Task> getTasksByAssignee(Long assigneeId, Pageable pageable);

    Task getTaskById(Long id);

    Page<Task> getTasksByFilters(String status, String priority, Long authorId, Long assigneeId, Pageable pageable);
}
