package org.example.tz_jwt.Task;

import org.example.tz_jwt.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setTitle("Integration Test Task");

        ResponseEntity<Task> response = restTemplate.postForEntity("/api/tasks", task, Task.class);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Integration Test Task", response.getBody().getTitle());
    }

    @Test
    public void testGetTaskById() {
        Task task = new Task();
        task.setTitle("Test Task");

        ResponseEntity<Task> createResponse = restTemplate.postForEntity("/api/tasks", task, Task.class);
        Long taskId = createResponse.getBody().getId();

        ResponseEntity<Task> getResponse = restTemplate.getForEntity("/api/tasks/" + taskId, Task.class);

        assertNotNull(getResponse);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Test Task", getResponse.getBody().getTitle());
    }
}