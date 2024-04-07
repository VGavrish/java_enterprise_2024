//package com.hillel.app.validation;
//
//import com.hillel.app.API.UsersApiController;
//
//import com.hillel.app.security.filter.JwtAuthorizationFilter;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
//import org.springframework.test.web.servlet.MockMvc;
//import com.hillel.app.services.UserCreator;
//import com.hillel.app.services.UserServiceImpl;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Slf4j
//@Import(WithSecurityContextTestExecutionListener.class)
//@WebMvcTest(value = {UsersApiController.class}, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = JwtAuthorizationFilter.class))
//public class UserCreatorValidationTest {
//    private MockMvc mockMvc;
//    @Autowired
//    private WebApplicationContext context;
//    @MockBean
//    private UserServiceImpl userService;
//    @MockBean UserCreator userCreator;
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//    @BeforeEach
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
//    }
//
//    @Test
//    @WithMockUser(username = "admin", roles = "ADMIN")
//    public void createUserWithInvalidUserNameNegative() throws Exception {
//        log.debug("Starting createUserWithInvalidUserNameNegative test");
//
//        mockMvc.perform(post("/api/users")//.with(user("admin").roles("ADMIN")).with(csrf().asHeader())
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"userName\":\"a\",\"password\":\"ValidPassword123!\",\"email\":\"email@mail.com\"}"))
//                        .andExpect(status().isBadRequest());
//
//        log.debug("createUserWithInvalidUserNameNegative test completed with expected status: 400 Bad Request");
//    }
//
//    @Test
//    public void createUserWithInvalidPasswordNegative() throws Exception {
//    log.debug("Starting createUserWithInvalidPasswordNegative test");
//
//    mockMvc.perform(post("/api/users")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content("{\"userName\":\"ValidUserName\",\"password\":\"123\",\"email\":\"email@mail.com\"}"))
//            .andExpect(status().isBadRequest());
//
//    log.debug("createUserWithInvalidPasswordNegative test completed with expected status: 400 Bad Request");
//    }
//}
