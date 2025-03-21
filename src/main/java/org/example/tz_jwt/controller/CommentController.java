package org.example.tz_jwt.controller;

import org.example.tz_jwt.model.Comment;
import org.example.tz_jwt.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @Operation(summary = "Add a comment", description = "Adds a new comment to a task")
    @ApiResponse(responseCode = "200", description = "Comment added successfully")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment addedComment = commentService.addComment(comment);
        return ResponseEntity.ok(addedComment);
    }

    @GetMapping("/task/{taskId}")
    @Operation(summary = "Get comments by task ID", description = "Returns a paginated list of comments for a task")
    @ApiResponse(responseCode = "200", description = "Comments retrieved successfully")
    public ResponseEntity<Page<Comment>> getCommentsByTaskId(
            @Parameter(description = "ID of the task", required = true)
            @PathVariable Long taskId,
            Pageable pageable) {
        Page<Comment> comments = commentService.getCommentsByTaskId(taskId, pageable);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/author/{authorId}")
    @Operation(summary = "Get comments by author ID", description = "Returns a paginated list of comments by an author")
    @ApiResponse(responseCode = "200", description = "Comments retrieved successfully")
    public ResponseEntity<Page<Comment>> getCommentsByAuthorId(
            @Parameter(description = "ID of the author", required = true)
            @PathVariable Long authorId,
            Pageable pageable) {
        Page<Comment> comments = commentService.getCommentsByAuthorId(authorId, pageable);
        return ResponseEntity.ok(comments);
    }
}
