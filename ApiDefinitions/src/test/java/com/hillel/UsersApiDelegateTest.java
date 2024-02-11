package com.hillel;

import exception.GlobalExceptionHandler;
import exception.UserNotFoundException;
import openapitools.model.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import services.UserCreator;
import services.UserServiceImpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import({GlobalExceptionHandler.class, UserCreator.class})
@SpringBootTest
@AutoConfigureMockMvc
public class UsersApiDelegateTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userServiceImp;

    private static final String RESPONSE_JSON_PATH = "src/test/resources/userResponse.json";

    @Test
    public void testGetAllUsersPositive() throws Exception {
        List<UserDto> userList = new ArrayList<>();
        userList.add(new UserDto());
        when(userServiceImp.getAllUsers()).thenReturn(userList);
        mockMvc.perform(get("/api/users")
                .header("Authorization", "Bearer someToken"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(userServiceImp).getAllUsers();
    }

    @Test
    public void testCreateUserPositive() throws Exception{
        UserDto userDto = new UserDto();
        when(userServiceImp.createUser(any(UserDto.class))).thenReturn(userDto);

        String userJson = new String(Files.readAllBytes(Paths.get(RESPONSE_JSON_PATH)));
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAllUsersNegativeUserNotFound() throws Exception {
        doThrow(new UserNotFoundException("User not found")).when(userServiceImp).getAllUsers();
        mockMvc.perform(get("/api/users").header("Authorization", "Bearer someToken"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateUserNegativeBadRequest() throws Exception {
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"invalid\":data}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateUserPositive() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setUserName("createdUser");
        userDto.setEmail("createdEmail@mail.com");
        userDto.setPassword("ValidPassword123");

        when(userServiceImp.updateUser(eq(1), any(UserDto.class))).thenReturn(userDto);
        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"createdUser\",\"email\":\"createdEmail@mail.com\",\"password\":\"ValidPassword123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName").value("createdUser"))
                .andExpect(jsonPath("$.email").value("createdEmail@mail.com"));

        verify(userServiceImp).updateUser(eq(1), any(UserDto.class));
    }

    @Test
    public void testUpdateUserNegativeUserNotFound() throws Exception {
        when(userServiceImp.updateUser(eq(1), any(UserDto.class)))
                .thenThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"nonexistent\",\"email\":\"nonexistent@example.com\", \"password\":\"InvalidPassword\"}"))
                .andExpect(status().isNotFound());

        verify(userServiceImp).updateUser(eq(1),any(UserDto.class));
    }

    @Test
    public void createUserWithInvalidDataShouldReturnBadRequest() throws Exception {
        String userJson = "{\"userName\":\"ab\",\"password\":\"123\",\"email\":\"invalid-email\"}";
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$[0]").isString());
    }
}
