package org.example.tz_jwt.controller;

import jakarta.validation.Valid;
import org.example.tz_jwt.model.Task;
import org.example.tz_jwt.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @Operation(summary = "Create a new task", description = "Creates a new task with the provided details")
    @ApiResponse(responseCode = "201", description = "Task created successfully")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a task", description = "Updates an existing task by ID")
    @ApiResponse(responseCode = "200", description = "Task updated successfully")
    @ApiResponse(responseCode = "404", description = "Task not found")
    public ResponseEntity<Task> updateTask(
            @Parameter(description = "ID of the task to be updated", required = true)
            @PathVariable Long id,
            @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a task by ID", description = "Returns a task by its ID")
    @ApiResponse(responseCode = "200", description = "Task found")
    @ApiResponse(responseCode = "404", description = "Task not found")
    public ResponseEntity<Task> getTaskById(
            @Parameter(description = "ID of the task to be retrieved", required = true)
            @PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }
    // Удаление задачи
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task", description = "Deletes a task by ID")
    @ApiResponse(responseCode = "204", description = "Task deleted successfully")
    @ApiResponse(responseCode = "404", description = "Task not found")
    public ResponseEntity<Void> deleteTask(
            @Parameter(description = "ID of the task to be deleted", required = true)
            @PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // Изменение статуса задачи
    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> changeTaskStatus(@PathVariable Long id, @RequestParam String status) {
        Task updatedTask = taskService.changeTaskStatus(id, status);
        return ResponseEntity.ok(updatedTask);
    }

    // Изменение приоритета задачи
    @PatchMapping("/{id}/priority")
    public ResponseEntity<Task> changeTaskPriority(@PathVariable Long id, @RequestParam String priority) {
        Task updatedTask = taskService.changeTaskPriority(id, priority);
        return ResponseEntity.ok(updatedTask);
    }

    // Назначение исполнителя задачи
    @PatchMapping("/{taskId}/assignee")
    public ResponseEntity<Task> assignTaskToUser(@PathVariable Long taskId, @RequestParam Long userId) {
        Task updatedTask = taskService.assignTaskToUser(taskId, userId);
        return ResponseEntity.ok(updatedTask);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<Page<Task>> getTasksByAuthor(
            @PathVariable Long authorId,
            Pageable pageable) {
        Page<Task> tasks = taskService.getTasksByAuthor(authorId, pageable);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/assignee/{assigneeId}")
    public ResponseEntity<Page<Task>> getTasksByAssignee(
            @PathVariable Long assigneeId,
            Pageable pageable) {
        Page<Task> tasks = taskService.getTasksByAssignee(assigneeId, pageable);
        return ResponseEntity.ok(tasks);
    }

    // Получение задач с фильтрацией и пагинацией
    @GetMapping
    @Operation(summary = "Get tasks with filters", description = "Returns a paginated list of tasks based on filters")
    @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully")
    public ResponseEntity<Page<Task>> getTasks(
            @Parameter(description = "Filter by status") @RequestParam(required = false) String status,
            @Parameter(description = "Filter by priority") @RequestParam(required = false) String priority,
            @Parameter(description = "Filter by author ID") @RequestParam(required = false) Long authorId,
            @Parameter(description = "Filter by assignee ID") @RequestParam(required = false) Long assigneeId,
            Pageable pageable) {
        Page<Task> tasks = taskService.getTasksByFilters(status, priority, authorId, assigneeId, pageable);
        return ResponseEntity.ok(tasks);
    }

}
