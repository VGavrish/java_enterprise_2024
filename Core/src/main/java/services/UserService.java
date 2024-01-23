package services;

import dto.ExerciseSetDto;
import dto.WorkoutDto;
import entity.ExerciseSet;
import entity.TrainingProgram;
import entity.User;
import entity.Workout;
import Exception.UserNotFoundException;
import openapitools.model.UserDto;

import java.util.List;

public interface UserService {
    UserDto mapUserToDto(User user);
    User mapDtoToUser(UserDto userDto);
    WorkoutDto mapWorkoutToDto(Workout workout);
    ExerciseSetDto mapExerciseSetToDto(ExerciseSet exerciseSet);
    UserDto createUser(UserDto UserDto);
    UserDto updateUser(Integer userId, UserDto userDto);
    UserDto getUserById(Long id) throws UserNotFoundException;
    User deleteUser(Long id);
    List<UserDto> getAllUsers();
    void addTrainingProgramToUser(Long userId, TrainingProgram program);
    void remoteTrainingProgramFromUser(Long userId, TrainingProgram program);
    List<TrainingProgram> getTrainingProgramForUser(Long userId);
    void addWorkoutToUser(Long userId, Workout workout);
    WorkoutDto getWorkoutForUser(Long userId);
    List<WorkoutDto> getWorkoutForProgram(Long userId, Long programId);
    WorkoutDto getWorkoutById(Long userId, Long workoutId);
    void updateWorkout(Long userId, Workout workout);
    void deleteWorkout(Long userId, Long workoutId);

}
