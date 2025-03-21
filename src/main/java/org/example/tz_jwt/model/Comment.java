package org.example.tz_jwt.model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique ID of the comment")
    private Long id;

    @NotBlank(message = "Text is required")
    @Size(max = 500, message = "Text must be less than 500 characters")
    @Schema(description = "The text of the comment", example = "This is a test comment")
    private String text;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @Schema(description = "The task associated with the comment")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @Schema(description = "The author of the comment")
    private User author;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
    public Task getTask() {
        return task;
    }
    public void setTask(Task task) {
        this.task = task;
    }
}
