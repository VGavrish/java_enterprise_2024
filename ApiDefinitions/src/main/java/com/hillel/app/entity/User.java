package com.hillel.app.entity;

import jakarta.persistence.*;
import lombok.*;
import com.hillel.app.validation.CustomConstraint;
import com.hillel.app.validation.ValidationType;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @CustomConstraint(type = ValidationType.USERNAME)
    private String username;

    @CustomConstraint(type = ValidationType.PASSWORD)
    private String password;

    @CustomConstraint(type = ValidationType.EMAIL)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
