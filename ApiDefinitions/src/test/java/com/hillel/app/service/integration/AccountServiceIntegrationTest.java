package com.hillel.app.service.integration;

import com.hillel.app.API.AuthController;
import com.hillel.app.security.jwt.TokenProvider;
import dto.SignInRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class AccountServiceIntegrationTest {
    @Autowired
    AuthController authController;

    @Autowired
    TokenProvider tokenProvider;

    SignInRequestDTO signInRequestDTO;

    @Container
    public static PostgreSQLContainer<?> pgContainer = new PostgreSQLContainer<>("postgres:13.3")
            .withDatabaseName("any")
            .withUsername("user")
            .withPassword("pass");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + pgContainer.getJdbcUrl(),
                    "spring.datasource.username=" + pgContainer.getUsername(),
                    "spring.datasource.password=" + pgContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @BeforeEach
    void setup() {
        signInRequestDTO = new SignInRequestDTO("testADMIN", "testpassword");
    }

    @Test
    void signinTest() {
        Map<String, Object> signin = authController.signin(signInRequestDTO);
        assertNotNull(signin.get("auth_token"));
    }
}
