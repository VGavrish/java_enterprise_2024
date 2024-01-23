package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseSetDto {
    private Long exerciseId;
    private int repetitions;
    private int sets;
    private double weight;
    private boolean completed;
}
