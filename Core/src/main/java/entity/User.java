package entity;

import jakarta.persistence.*;
import lombok.*;
import validation.CustomConstraint;
import validation.ValidationType;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @CustomConstraint(type = ValidationType.USERNAME)
    private String userName;

    @CustomConstraint(type = ValidationType.PASSWORD)
    private String password;

    @CustomConstraint(type = ValidationType.EMAIL)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
