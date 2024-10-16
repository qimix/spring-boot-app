package ru.netology.spring_boot_app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class SpringBootAppAppDevTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    static GenericContainer devAppContainer = new GenericContainer("devapp:latest")
            .withExposedPorts(8080);

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + devAppContainer.getMappedPort(8080) + "/profile", String.class);
	    assertEquals(forEntityDev.getBody(), "Current profile is dev");
		System.out.println(forEntityDev.getBody());
    }
}
