package com.estrellaticona.vetcare.iam.domain.model.aggregates;

import com.estrellaticona.vetcare.iam.domain.model.commands.SignInCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.SignUpCommand;
import com.estrellaticona.vetcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    public User() {
    }

    public User(SignUpCommand command) {
        this.email = command.email();
        this.password = command.password();
    }

    public User(SignInCommand command) {
        this.email = command.email();
        this.password = command.password();
    }
}
