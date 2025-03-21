package org.example.tz_jwt.repository;

import org.example.tz_jwt.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Фильтрация по статусу
    Page<Task> findByStatus(String status, Pageable pageable);

    // Фильтрация по приоритету
    Page<Task> findByPriority(String priority, Pageable pageable);

    // Фильтрация по автору
    Page<Task> findByAuthorId(Long authorId, Pageable pageable);

    // Фильтрация по исполнителю
    Page<Task> findByAssigneeId(Long assigneeId, Pageable pageable);

    // Комбинированная фильтрация
    @Query("SELECT t FROM Task t WHERE " +
            "(:status IS NULL OR t.status = :status) AND " +
            "(:priority IS NULL OR t.priority = :priority) AND " +
            "(:authorId IS NULL OR t.author.id = :authorId) AND " +
            "(:assigneeId IS NULL OR t.assignee.id = :assigneeId)")
    Page<Task> findByFilters(
            @Param("status") String status,
            @Param("priority") String priority,
            @Param("authorId") Long authorId,
            @Param("assigneeId") Long assigneeId,
            Pageable pageable);
}