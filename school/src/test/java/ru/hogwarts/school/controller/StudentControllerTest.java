package ru.hogwarts.school.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.hogwarts.school.model.Student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;


    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void contextLoads() {
        assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudent() {
        ResponseEntity<Student> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/student/1", Student.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAddStudent() {
        Student student = new Student("Ivan", 30);

        ResponseEntity<Student> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/student", student, Student.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ivan", response.getBody().getName());
    }
}