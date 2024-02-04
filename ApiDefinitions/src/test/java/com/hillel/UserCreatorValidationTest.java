package com.hillel;

import API.UsersApiDelegate;
import exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import services.UserCreator;
import services.UserServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(UsersApiDelegate.class)
@Import({GlobalExceptionHandler.class, CustomValidationTest.class, UserCreator.class})
public class UserCreatorValidationTest {
    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private UserCreator userCreator;

    @MockBean
    private UserServiceImpl userService;

    @Test
    public void createUserWithInvalidUserNameNegative() throws Exception {
        log.debug("Starting createUserWithInvalidUserNameNegative test");

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"a\",\"password\":\"ValidPassword123!\",\"email\":\"email@mail.com\"}"))
                .andExpect(status().isBadRequest());

        log.debug("createUserWithInvalidUserNameNegative test completed with expected status: 400 Bad Request");
    }
@Test
public void createUserWithInvalidPasswordNegative() throws Exception {
    log.debug("Starting createUserWithInvalidPasswordNegative test");

    mockMvc.perform(post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"username\":\"ValidUserName\",\"password\":\"123\",\"email\":\"email@mail.com\"}"))
            .andExpect(status().isBadRequest());

    log.debug("createUserWithInvalidPasswordNegative test completed with expected status: 400 Bad Request");
}
}
