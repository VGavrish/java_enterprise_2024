package com.hillel.app.services;

import dto.WorkoutDto;
import entity.User;
import com.hillel.app.mapper.UserMapper;
import exception.UserNotFoundException;
import openapitools.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.hillel.app.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }
    public UserDto mapUserToDto(User user) {
        return userMapper.userToUserDto(user);
    }
    public User mapDtoToUser(UserDto userDto) {
        return userMapper.userDtoToUser(userDto);
    }
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapDtoToUser(userDto);
        userRepository.save(user);
        return mapUserToDto(user);
    }
    @Override
    public UserDto updateUser(Integer userId, UserDto userDto) {
        return new UserDto();
    }
    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::userToUserDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
    @Override
    public User deleteUser(Long id) {
        return null;
    }
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("Users not found");
        }
        return users.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }
    @Override
    public WorkoutDto getWorkoutForUser(Long userId) {
        return null;
    }
    @Override
    public List<WorkoutDto> getWorkoutForProgram(Long userId, Long programId) {
        return null;
    }
    @Override
    public WorkoutDto getWorkoutById(Long userId, Long workoutId) {
        return null;
    }
    @Override
    public void deleteWorkout(Long userId, Long workoutId) {
    }
}
