package services;

import dto.WorkoutDto;
import entity.User;
import exception.UserNotFoundException;
import openapitools.model.UserDto;

import java.util.List;

public interface UserService {
    UserDto mapUserToDto(User user);
    User mapDtoToUser(UserDto userDto);
    UserDto createUser(UserDto UserDto);
    UserDto updateUser(Integer userId, UserDto userDto);
    UserDto getUserById(Long id) throws UserNotFoundException;
    User deleteUser(Long id);
    List<UserDto> getAllUsers();
    WorkoutDto getWorkoutForUser(Long userId);
    List<WorkoutDto> getWorkoutForProgram(Long userId, Long programId);
    WorkoutDto getWorkoutById(Long userId, Long workoutId);
    void deleteWorkout(Long userId, Long workoutId);
//    void addTrainingProgramToUser(Long userId, TrainingProgram program);
//    void remoteTrainingProgramFromUser(Long userId, TrainingProgram program);
//    List<TrainingProgram> getTrainingProgramForUser(Long userId);
//    void addWorkoutToUser(Long userId, Workout workout);
//    void updateWorkout(Long userId, Workout workout);
//    WorkoutDto mapWorkoutToDto(Workout workout);
//    ExerciseSetDto mapExerciseSetToDto(ExerciseSet exerciseSet);
}
