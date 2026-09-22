package ru.hogwarts.school.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.model.Faculty;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
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

        // создать факультет, чтобы получить реальный id из БД
        ResponseEntity<Faculty> createResponse = restTemplate.postForEntity(
                getUrl(), new Faculty("Грифиндор", "Красный"), Faculty.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(createResponse.getBody()).isNotNull();
        Long facultyId = createResponse.getBody().getId();
        assertThat(facultyId).isNotNull();

        // обновить созданный факультет
        Faculty newFacultyInfo = new Faculty(facultyId, "Грифиндор", "Зеленый");

        HttpEntity<Faculty> requestEntity = new HttpEntity<>(newFacultyInfo);

        ResponseEntity<Faculty> response = restTemplate.exchange(
                getUrl()+"/"+facultyId, HttpMethod.PUT, requestEntity, Faculty.class);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        Faculty responseBody = response.getBody();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getId()).isEqualTo(facultyId);
        assertThat(responseBody.getName()).isEqualTo("Грифиндор");
        assertThat(responseBody.getColor()).isEqualTo("Зеленый");

    }


}


