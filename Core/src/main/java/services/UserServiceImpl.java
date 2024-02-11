package services;

import dto.WorkoutDto;
import entity.User;
import jakarta.validation.Valid;
import mapper.UserMapper;
import exception.UserNotFoundException;
import openapitools.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

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
    public UserDto createUser(@Valid UserDto userDto) {
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
        return new ArrayList<>();
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
//    @Override
//    public void addTrainingProgramToUser(Long userId, TrainingProgram program) {
//    }

//    @Override
//    public void remoteTrainingProgramFromUser(Long userId, TrainingProgram program) {
//    }

//    @Override
//    public List<TrainingProgram> getTrainingProgramForUser(Long userId) {
//        return null;
//    }

//    @Override
//    public void addWorkoutToUser(Long userId, Workout workout) {
//    }

//    public WorkoutDto mapWorkoutToDto(Workout workout) {
//        return WorkoutMapper.INSTANCE.workoutToWorkoutDto(workout);
//    }

//    public ExerciseSetDto mapExerciseSetToDto(ExerciseSet exerciseSet) {
//        return ExerciseSetMapper.INSTANCE.exerciseSetToExerciseSetDto(exerciseSet);
//    }

//    @Override
//    public void updateWorkout(Long userId, Workout workout) {
//    }
}
