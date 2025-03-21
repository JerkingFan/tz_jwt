package org.example.tz_jwt.Comment;

import org.example.tz_jwt.model.Comment;
import org.example.tz_jwt.repository.CommentRepository;
import org.example.tz_jwt.service.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void testAddComment() {
        Comment comment = new Comment();
        comment.setText("Test comment");

        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        Comment addedComment = commentService.addComment(comment);

        assertNotNull(addedComment);
        assertEquals("Test comment", addedComment.getText());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }
}