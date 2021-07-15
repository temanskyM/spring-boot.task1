package ru.netology.task1.rest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProfileControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    public static GenericContainer<?> devApp = new GenericContainer<>("devapp");
    public static GenericContainer<?> prodApp = new GenericContainer<>("prodapp");

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void getProfile_dev() {
        String url = "http://localhost:" + devApp.getMappedPort(8080) + "/profile";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals("Current profile is dev", response.getBody());
    }

    @Test
    void getProfile_prod() {
        String url = "http://localhost:" + prodApp.getMappedPort(8081) + "/profile";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals("Current profile is production", response.getBody());
    }

}