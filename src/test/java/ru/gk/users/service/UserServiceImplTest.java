package ru.gk.users.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gk.users.dto.UserRequest;
import ru.gk.users.entity.User;
import ru.gk.users.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequest userRequest;
    private User user;

    @BeforeEach
    void setUp() {
        userRequest = UserRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .gender("gender")
                .dateOfBirth(new Date())
                .email("email")
                .phoneNumber("1").build();

        user = User.builder()
                .userId(1L)
                .firstName("firstName")
                .lastName("lastName")
                .gender("gender")
                .dateOfBirth(new Date())
                .email("email")
                .phoneNumber("1").build();
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(any())).thenReturn(user);
        User createdUser = userService.createUser(userRequest);
        assertEquals(userRequest.getFirstName(), createdUser.getFirstName());
    }

    @Test
    void testUpdateUser() {
        userRequest.setFirstName("newname");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User updatedUser = userService.updateUser(userRequest, 1L);
        assertEquals(userRequest.getFirstName(), updatedUser.getFirstName());
    }

    @Test
    void testUpdateUserNotFound() {
        Long userId = 1L;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> userService.updateUser(userRequest, userId));
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User getUser = userService.getUserById(userId);
        assertEquals(user, getUser);
    }

    @Test
    void testDeleteUserById() {
        Long userId = 1L;
        userService.deleteUserById(userId);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(userId);
    }
}
