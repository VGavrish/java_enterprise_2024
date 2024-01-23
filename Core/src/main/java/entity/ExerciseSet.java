package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
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
