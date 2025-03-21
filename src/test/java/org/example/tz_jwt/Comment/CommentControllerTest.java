package org.example.tz_jwt.Comment;

import org.example.tz_jwt.controller.CommentController;
import org.example.tz_jwt.model.Comment;
import org.example.tz_jwt.service.CommentService;
import org.hibernate.query.Page;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @Test
    public void testAddComment() {
        Comment comment = new Comment();
        comment.setText("Test comment");

        when(commentService.addComment(any(Comment.class))).thenReturn(comment);

        ResponseEntity<Comment> response = commentController.addComment(comment);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test comment", response.getBody().getText());
        verify(commentService, times(1)).addComment(any(Comment.class));
    }

}