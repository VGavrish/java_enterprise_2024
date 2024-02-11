package entity;

import jakarta.persistence.*;
import lombok.*;
import validation.CustomConstraint;
import validation.ValidationType;


@Data
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    @CustomConstraint(type = ValidationType.USERNAME)
    private String userName;

    @CustomConstraint(type = ValidationType.PASSWORD)
    private String password;

    @CustomConstraint(type = ValidationType.EMAIL)
    private String email;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<TrainingProgram> trainingProgramList;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Workout> workoutList;
}
