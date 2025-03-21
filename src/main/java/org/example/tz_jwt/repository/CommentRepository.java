package org.example.tz_jwt.repository;

import org.example.tz_jwt.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTaskId(Long taskId);

    List<Comment> findByAuthorId(Long authorId);
    Page<Comment> findByTaskId(Long taskId, Pageable pageable);

}
