package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExerciseSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long exerciseId;
    private int repetitions;
    private int sets;
    private double weight;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

}
