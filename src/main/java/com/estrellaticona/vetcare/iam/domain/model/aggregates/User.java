package com.estrellaticona.vetcare.iam.domain.model.aggregates;

import com.estrellaticona.vetcare.iam.domain.model.commands.SignInCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.SignUpCommand;
import com.estrellaticona.vetcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String speciality;

    @NotNull
    @Column(nullable = false)
    @Digits(integer = 8, fraction = 0)
    private Integer dni;

    @NotNull
    @Column(nullable = false)
    @Digits(integer = 9, fraction = 0)
    private Integer phone;

    public User() {
    }

    public User(SignUpCommand command) {
        this.email = command.email();
        this.password = command.password();
        this.name = command.name();
        this.speciality = command.speciality();
        this.dni = command.dni();
        this.phone = command.phone();
    }

    public User(SignInCommand command) {
        this.email = command.email();
        this.password = command.password();
    }
}
