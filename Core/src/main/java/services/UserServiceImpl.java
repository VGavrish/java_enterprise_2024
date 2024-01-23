package services;

import dto.ExerciseSetDto;
import dto.WorkoutDto;
import entity.ExerciseSet;
import entity.TrainingProgram;
import entity.User;
import entity.Workout;
import mapper.ExerciseSetMapper;
import mapper.UserMapper;
import Exception.UserNotFoundException;
import mapper.WorkoutMapper;
import openapitools.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final WorkoutMapper workoutMapper;
    private final ExerciseSetMapper exerciseSetMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, WorkoutMapper workoutMapper, ExerciseSetMapper exerciseSetMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.workoutMapper = workoutMapper;
        this.exerciseSetMapper = exerciseSetMapper;
    }
    public UserDto mapUserToDto(User user) {
        return userMapper.userToUserDto(user);
    }

    public User mapDtoToUser(UserDto userDto) {
        return userMapper.userDtoToUser(userDto);
    }

    public WorkoutDto mapWorkoutToDto(Workout workout) {
        return WorkoutMapper.INSTANCE.workoutToWorkoutDto(workout);
    }

    public ExerciseSetDto mapExerciseSetToDto(ExerciseSet exerciseSet) {
        return ExerciseSetMapper.INSTANCE.exerciseSetToExerciseSetDto(exerciseSet);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return new UserDto();
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
        return null;
    }

    @Override
    public void addTrainingProgramToUser(Long userId, TrainingProgram program) {
    }

    @Override
    public void remoteTrainingProgramFromUser(Long userId, TrainingProgram program) {
    }

    @Override
    public List<TrainingProgram> getTrainingProgramForUser(Long userId) {
        return null;
    }

    @Override
    public void addWorkoutToUser(Long userId, Workout workout) {

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
    public void updateWorkout(Long userId, Workout workout) {

    }
    @Override
    public void deleteWorkout(Long userId, Long workoutId) {

    }
}
