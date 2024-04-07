//package com.hillel;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hillel.app.API.AuthController;
//import com.hillel.app.security.jwt.TokenProvider;
//import com.hillel.app.services.UserService;
//import com.hillel.app.services.UserServiceImpl;
//import dto.SignInRequestDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class AuthControllerTest {
//    private MockMvc mockMvc;
//    @InjectMocks
//    private AuthController authController;
//    @Mock
//    AuthenticationProvider authenticationProvider;
//    @Mock
//    private TokenProvider tokenProvider;
//    @Mock
//    UserServiceImpl userServiceImpl;
//
//    @BeforeEach
//    public void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
//    }
//
//    @Test
//    public void testSigninSuccess() throws Exception {
//        String username = "testuser";
//        String password = "testpassword";
//        String token = "test_token";
//
//        when(authenticationProvider.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//                .thenReturn(new UsernamePasswordAuthenticationToken(username, password));
//
//        when(tokenProvider.createToken(any())).thenReturn(token);
//
//        mockMvc.perform(post("/auth/signin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(new SignInRequestDTO(username, password))))
//                .andExpect(status().isOk());
//    }
//
////    @Test
////    public void testSigninFailure() throws Exception {
////        String username = "testuser";
////        String password = "invalid_password";
////
////        when(authenticationProvider.authenticate(any(UsernamePasswordAuthenticationToken.class)))
////                .thenThrow(new BadCredentialsException("Invalid username or password"));
////
////        mockMvc.perform(post("/auth/signin")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(new ObjectMapper().writeValueAsString(new SignInRequestDTO(username, password))))
////                .andExpect(status().isUnauthorized());
////
////        verify(authenticationProvider, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
////        verify(tokenProvider, never()).createToken(any());
////    }
//}
