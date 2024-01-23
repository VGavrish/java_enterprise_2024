package API;

import openapitools.api.UsersApi;
import openapitools.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import services.UserServiceImpl;


import java.util.List;

@Controller
public class UsersApiDelegate implements UsersApi {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UsersApiDelegate(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    @Override
    public ResponseEntity<List<UserDto>> getAllUsers(String authorization, Integer page) {
        List<UserDto> users = userServiceImpl.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto, String requestId) {
        UserDto createdUser = userServiceImpl.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Integer userId, UserDto userDto, String xCorrelationID) {
        UserDto updatedUser = userServiceImpl.updateUser(userId, userDto);
        return ResponseEntity.ok(updatedUser);
    }


}
