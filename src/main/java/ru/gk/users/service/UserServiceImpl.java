package ru.gk.users.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gk.users.dto.UserRequest;
import ru.gk.users.entity.User;
import ru.gk.users.repository.UserRepository;

@Service
@Log4j2
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    @Transactional
    public User createUser(UserRequest request) {
        log.info("createUser request:  {} ", request);
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .city(request.getCity())
                .avatarUrl(request.getAvatarUrl())
                .aboutMe(request.getAboutMe())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();

        user = userRepository.save(user);

        log.info("user created: {} ", user.getUserId());
        return user;
    }

    @Override
    @Transactional
    public User updateUser(UserRequest request, Long userId) {
        log.info("updateUser request:  {} ", request);
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("not found"));

        if (existingUser != null) {
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            existingUser.setMiddleName(request.getMiddleName());
            existingUser.setGender(request.getGender());
            existingUser.setDateOfBirth(request.getDateOfBirth());
            existingUser.setCity(request.getCity());
            existingUser.setAvatarUrl(request.getAvatarUrl());
            existingUser.setAboutMe(request.getAboutMe());
            existingUser.setNickname(request.getNickname());
            existingUser.setEmail(request.getEmail());
            existingUser.setPhoneNumber(request.getPhoneNumber());
            userRepository.save(existingUser);
        }
        return existingUser;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    @Transactional
    public void deleteUserById(Long userId) {
        log.info("delete user {} ", userId);
        userRepository.deleteById(userId);
    }
}
