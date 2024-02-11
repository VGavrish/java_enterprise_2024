package API;

import exception.UserNotFoundException;
import jakarta.validation.Valid;
import openapitools.api.UsersApi;
import openapitools.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.UserCreator;
import services.UserServiceImpl;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersApiDelegate implements UsersApi {
    private static final Logger log = LoggerFactory.getLogger(UsersApiDelegate.class);

    private final UserServiceImpl userServiceImpl;
    private final UserCreator userCreator;

    @Autowired
    public UsersApiDelegate(UserServiceImpl userServiceImpl, UserCreator userCreator) {
        this.userServiceImpl = userServiceImpl;
        this.userCreator = userCreator;
    }
    @GetMapping
    @Override
    public ResponseEntity<List<UserDto>> getAllUsers(String authorization, Integer page) {
        log.info("Request to get all users");
        List<UserDto> users = userServiceImpl.getAllUsers();
        if (users.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping
    @Override
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto, String requestId) {
        log.info("Request to create user: {}", userDto);
        UserDto createdUser = userCreator.createUser(
                userDto.getUserName(),
                userDto.getPassword(),
                userDto.getEmail()
        );
        return ResponseEntity.ok(createdUser);
    }
    @PutMapping("/{userId}")
    @Override
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {
        log.info("Request to update user");
        UserDto updatedUser = userServiceImpl.updateUser(userId, userDto);
        return ResponseEntity.ok(updatedUser);
    }


}
