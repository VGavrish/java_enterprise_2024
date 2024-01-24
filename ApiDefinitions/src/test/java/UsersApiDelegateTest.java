import API.UsersApiDelegate;
import exception.UserNotFoundException;
import openapitools.model.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import services.UserServiceImpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsersApiDelegate.class)
public class UsersApiDelegateTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userServiceImp;

    private static final String RESPONSE_JSON_PATH = "src/test/resources/userResponse.json";

    @BeforeEach
    void setUp() {
        userServiceImp = mock(UserServiceImpl.class);
        UsersApiDelegate usersApiDelegate = new UsersApiDelegate(userServiceImp);
        mockMvc = MockMvcBuilders.standaloneSetup(usersApiDelegate).build();
    }

    @Test
    public void testGetAllUsersPositive() throws Exception {
        when(userServiceImp.getAllUsers()).thenReturn(List.of(new UserDto()));
        mockMvc.perform(get("/users")
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
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAllUsersNegativeUserNotFound() throws Exception {
        doThrow(new UserNotFoundException("User not found")).when(userServiceImp).getAllUsers();
        mockMvc.perform(get("/users").header("Authorization", "Bearer someToken"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateUserNegativeBadRequest() throws Exception {
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"invalid\":\"data\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateUserPositive() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setUsername("updatedUser");
        userDto.setEmail("updated@mail.com");

        when(userServiceImp.updateUser(eq(1), any(UserDto.class))).thenReturn(userDto);
        mockMvc.perform(post("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"updatedUser\",\"email\":\"updated@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("updatedUser"))
                .andExpect(jsonPath("$.email").value("updated@example.com"));

        verify(userServiceImp).updateUser(eq(1), any(UserDto.class));
    }

    @Test
    public void testUpdateUserNegativeUserNotFound() throws Exception {
        when(userServiceImp.updateUser(eq(1), any(UserDto.class)))
                .thenThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"nonexistent\",\"email\":\"nonexistent@example.com\"}"))
                .andExpect(status().isNotFound());

        verify(userServiceImp).updateUser(eq(1),any(UserDto.class));
    }
}
