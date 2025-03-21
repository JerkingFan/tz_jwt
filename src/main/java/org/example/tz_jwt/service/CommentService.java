package org.example.tz_jwt.service;

import org.example.tz_jwt.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment); // Добавление комментария// Получение комментариев по задаче
    List<Comment> getCommentsByAuthorId(Long authorId); // Получение комментариев по автору
    Page<Comment> getCommentsByTaskId(Long taskId, Pageable pageable);
}
