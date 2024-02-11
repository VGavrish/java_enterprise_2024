//package entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "exerciseSet")
//public class ExerciseSet {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Long exerciseId;
//    private int repetitions;
//    private int sets;
//    private double weight;
//    private boolean completed;
//
//    @ManyToOne
//    @JoinColumn(name = "workout_id")
//    private Workout workout;
//
//}
