package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import openapitools.model.UserDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDto {
    private Long id;
    private String date;
    private String notes;
    private List<ExerciseSetDto> exerciseSets;
    private UserDto user;
}
