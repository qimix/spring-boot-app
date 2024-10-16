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
class SpringBootAppAppProdTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    static GenericContainer prodAppContainer = new GenericContainer("prodapp:latest")
            .withExposedPorts(8081);

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntityProd = restTemplate.getForEntity("http://localhost:" + prodAppContainer.getMappedPort(8081) + "/profile", String.class);
        assertEquals(forEntityProd.getBody(), "Current profile is production");
        System.out.println(forEntityProd.getBody());
    }
}

