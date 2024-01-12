package ru.gk.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.gk.users.dto.UserRequest;
import ru.gk.users.entity.User;
import ru.gk.users.service.SubscriptionService;
import ru.gk.users.service.UserService;
import ru.gk.users.service.UserSkillsService;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private UserSkillsService userSkillsService;

    @MockBean
    private SubscriptionService subscriptionService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetUserById() throws Exception {
        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setUserId(userId);
        when(userService.getUserById(userId)).thenReturn(expectedUser);

        mockMvc.perform(get("/api/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedUser)));
    }


    @Test
    void testCreateUser() throws Exception {
        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setUserId(userId);
        when(userService.createUser(any())).thenReturn(expectedUser);

        UserRequest userRequest = UserRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .gender("gender")
                .dateOfBirth(new Date())
                .email("email")
                .phoneNumber("1").build();

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedUser)));
    }


}