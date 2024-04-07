//package com.hillel;
//
//import com.hillel.app.API.UsersApiController;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import com.hillel.app.services.UserCreator;
//import com.hillel.app.services.UserServiceImpl;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@Slf4j
//@AutoConfigureMockMvc
//@ContextConfiguration
//@WebMvcTest({UsersApiController.class})
//public class UserCreatorValidationTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private UserServiceImpl userService;
//    @MockBean UserCreator userCreator;
//    @Test
//    public void createUserWithInvalidUserNameNegative() throws Exception {
//        log.debug("Starting createUserWithInvalidUserNameNegative test");
//
//        mockMvc.perform(post("/api/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"userName\":\"a\",\"password\":\"ValidPassword123!\",\"email\":\"email@mail.com\"}"))
//                .andExpect(status().isBadRequest());
//
//        log.debug("createUserWithInvalidUserNameNegative test completed with expected status: 400 Bad Request");
//    }
//@Test
//public void createUserWithInvalidPasswordNegative() throws Exception {
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
