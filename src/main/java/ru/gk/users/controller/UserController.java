package ru.gk.users.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gk.users.dto.UserRequest;
import ru.gk.users.entity.User;
import ru.gk.users.service.SubscriptionService;
import ru.gk.users.service.UserService;
import ru.gk.users.service.UserSkillsService;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserSkillsService userSkillsService;
    private final SubscriptionService subscriptionService;

    @Operation(summary = "Get user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "ERROR")})
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @Operation(summary = "Reg user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "CREATED"),
                    @ApiResponse(responseCode = "500", description = "ERROR")
            })
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Update user",
            responses = {
                    @ApiResponse(responseCode = "202", description = "ACCEPTED"),
                    @ApiResponse(responseCode = "500", description = "ERROR")
            })
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.updateUser(userRequest, userId), HttpStatus.ACCEPTED);
    }


    @Operation(summary = "Delete user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "ERROR")
            })
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{userId}/addSkill/{skill}")
    @Operation(summary = "Add skill",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "ERROR")
            })
    public ResponseEntity<Void> addSkill(
            @PathVariable Long userId,
            @PathVariable String skill) {
        userSkillsService.addSkill(userId, skill);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/removeSkill/{skillId}")
    @Operation(summary = "Remove skill",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "ERROR")
            })
    public ResponseEntity<Void> RemoveSkill(
            @PathVariable Long userId,
            @PathVariable Long skillId) {
        userSkillsService.removeSkill(userId, skillId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/{userId}/subscribe/{targetUserId}")
    @Operation(summary = "Subscribe",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "ERROR")
            })
    public ResponseEntity<Void> subscribe(
            @PathVariable Long userId,
            @PathVariable Long targetUserId) {
        subscriptionService.subscribe(userId, targetUserId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{userId}/unSubscribe/{targetUserId}")
    @Operation(summary = "Unsubscribe",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "ERROR")
            })
    public ResponseEntity<Void> unSubscribe(
            @PathVariable Long userId,
            @PathVariable Long targetUserId) {
        subscriptionService.unSubscribe(userId, targetUserId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
