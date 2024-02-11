package services;

import openapitools.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import validation.CustomConstraint;
import validation.ValidationType;

@Component
@Validated
public class UserCreator {
    private final UserServiceImpl userServiceImpl;
    private static final Logger log = LoggerFactory.getLogger(UserCreator.class);


    public UserCreator(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    public UserDto createUser(@Validated @CustomConstraint(type = ValidationType.USERNAME) String userName,
                              @Validated @CustomConstraint(type = ValidationType.PASSWORD) String password,
                              @Validated @CustomConstraint(type = ValidationType.EMAIL) String email) {
        log.info("Creating user with userName: {}, password: {}, email: {}", userName, password, email);
        UserDto userDto = new UserDto();
        userDto.setUserName(userName);
        userDto.setPassword(password);
        userDto.setEmail(email);

        return userServiceImpl.createUser(userDto);
    }
}
