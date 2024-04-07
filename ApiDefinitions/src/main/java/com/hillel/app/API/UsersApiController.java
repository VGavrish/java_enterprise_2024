package com.hillel.app.API;

import jakarta.validation.Valid;
import logger.aspect.Logging;
import openapitools.api.UsersApi;
import openapitools.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.hillel.app.services.UserCreator;
import com.hillel.app.services.UserServiceImpl;


import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('USER')")
public class UsersApiController implements UsersApi {
    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final UserServiceImpl userServiceImpl;
    private final UserCreator userCreator;

    public UsersApiController(UserServiceImpl userServiceImpl, UserCreator userCreator) {
        this.userServiceImpl = userServiceImpl;
        this.userCreator = userCreator;
    }
    @Logging
    @GetMapping
    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Request to get all users");
        List<UserDto> users = userServiceImpl.getAllUsers();
        if (users.isEmpty()) {
            log.warn("Users not found");
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(users);
    }

    @Logging
    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        UserDto userDto = userServiceImpl.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }
    @Logging
    @PostMapping
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto, String requestId) {
        log.info("Request to create user: {}", userDto);
        UserDto createdUser = userCreator.createUser(
                userDto.getUserName(),
                userDto.getPassword(),
                userDto.getEmail()
        );
        return ResponseEntity.ok(createdUser);
    }
    @Logging
    @PutMapping("/{userId}")
    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {
        log.info("Request to update user");
        UserDto updatedUser = userServiceImpl.updateUser(userId, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @Logging
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        userServiceImpl.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
