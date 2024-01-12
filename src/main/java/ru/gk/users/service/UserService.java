package ru.gk.users.service;

import ru.gk.users.dto.UserRequest;
import ru.gk.users.entity.User;

public interface UserService {
    User createUser(UserRequest request);

    User updateUser(UserRequest request, Long userId);

    User getUserById(Long userId);

    void deleteUserById(Long userId);

}
