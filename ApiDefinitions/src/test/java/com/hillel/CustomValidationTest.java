package com.hillel;

import entity.User;
import jakarta.validation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CustomValidationTest {
    private static Validator validator;
    @BeforeAll
    public static void setupValidatorInstance() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void testInvalidUserName() {
        User user = new User();
        user.setUserName("ab");
        user.setPassword("qwerty123");
        user.setEmail("user@mail.com");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Validation should fail for user name");
    }
    @Test
    public void testInvalidPassword() {
        User user = new User();
        user.setUserName("ValidUser");
        user.setPassword("123");
        user.setEmail("user@mail.com");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertFalse(violations.isEmpty(), "Validation should fail for invalid password");
    }
}
