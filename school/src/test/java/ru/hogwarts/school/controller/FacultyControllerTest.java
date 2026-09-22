package ru.hogwarts.school.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private  FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;


    public String getUrl(){
        return "http://localhost:" + port + "/faculty";
    }

    @Test
    public void testPutFaculty() {

        Long facultyId = 1L;
        Faculty newFacultyInfo = new Faculty(facultyId,"Грифиндор","Зеленый");

        HttpEntity<Faculty> requestEntity = new HttpEntity<>(newFacultyInfo);

        ResponseEntity<Faculty> response = restTemplate.exchange(
                getUrl()+"/"+facultyId, HttpMethod.PUT,requestEntity, Faculty.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Faculty responseBody = response.getBody();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getId()).isEqualTo(facultyId);
        assertThat(responseBody.getName()).isEqualTo("Грифиндор");
        assertThat(responseBody.getColor()).isEqualTo("Зеленый");

    }


}


