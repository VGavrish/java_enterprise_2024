package API;

import exception.UserNotFoundException;
import jakarta.validation.Valid;
import logger.aspect.Logging;
import openapitools.api.UsersApi;
import openapitools.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.UserCreator;
import services.UserServiceImpl;


import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('USER')")
public class UsersApiController implements UsersApi {
    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final UserServiceImpl userServiceImpl;
    private final UserCreator userCreator;

    @Autowired
    public UsersApiController(UserServiceImpl userServiceImpl, UserCreator userCreator) {
        this.userServiceImpl = userServiceImpl;
        this.userCreator = userCreator;
    }
    @Logging
    @GetMapping
    @Override
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestHeader String authorization) {
        log.info("Request to get all users");
        try {
            List<UserDto> users = userServiceImpl.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            log.error("Users not found: {}", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        } catch (Exception e) {
            log.error("Internal Server Error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
}
