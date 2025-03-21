package org.example.tz_jwt.service;

import org.example.tz_jwt.model.Comment;
import org.example.tz_jwt.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        // Валидация данных (например, проверка, что текст комментария не пустой)
        if (comment.getText() == null || comment.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Comment text cannot be empty");
        }

        // Сохранение комментария в базе данных
        return commentRepository.save(comment);
    }

    @Override
    public Page<Comment> getCommentsByTaskId(Long taskId, Pageable pageable) {
        return commentRepository.findByTaskId(taskId, pageable);
    }

    @Override
    public List<Comment> getCommentsByAuthorId(Long authorId) {
        // Получение всех комментариев, оставленных конкретным автором
        return commentRepository.findByAuthorId(authorId);
    }
}

