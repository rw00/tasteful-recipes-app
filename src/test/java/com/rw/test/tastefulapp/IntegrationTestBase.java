package com.rw.test.tastefulapp;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTestBase {
    @Container
    static final PostgreSQLContainer<?> POSTGRES_DB = new PostgreSQLContainer<>("postgres:16.6")
            .withDatabaseName("app-db")
            .withUsername("root")
            .withPassword("secret");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", POSTGRES_DB::getJdbcUrl);
        propertyRegistry.add("spring.datasource.username", POSTGRES_DB::getUsername);
        propertyRegistry.add("spring.datasource.password", POSTGRES_DB::getPassword);
    }
}
