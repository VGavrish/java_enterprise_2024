package entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Embeddable
public class ExerciseSet {
    private Long exerciseId;
    private int repetitions;
    private int sets;
    private double weight;
    private boolean completed;

}
