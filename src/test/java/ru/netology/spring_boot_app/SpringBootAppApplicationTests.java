package ru.netology.spring_boot_app;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class SpringBootAppApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    static GenericContainer devAppContainer = new GenericContainer("devapp:latest")
            .withExposedPorts(8080);

    @Container
    static GenericContainer prodAppContainer = new GenericContainer("prodapp:latest")
            .withExposedPorts(8081);

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + devAppContainer.getMappedPort(8080) + "/profile", String.class);
		ResponseEntity<String> forEntityProd = restTemplate.getForEntity("http://localhost:" + prodAppContainer.getMappedPort(8081) + "/profile", String.class);
        System.out.println(forEntityDev.getBody() + "\n" + forEntityProd.getBody());
    }
}
