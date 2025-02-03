package com.estrellaticona.vetcare.clients.domain.model.aggregates;

import com.estrellaticona.vetcare.clients.domain.model.commands.CreateClientCommand;
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
public class Client extends AuditableAbstractAggregateRoot<Client> {
    @NotNull
    private Long doctorId;

    @NotBlank
    private String name;

    @NotNull
    @Column(nullable = false)
    @Digits(integer = 8, fraction = 0)
    private Integer dni;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    @Digits(integer = 9, fraction = 0)
    private Integer phone;

    public Client() {
    }

    public Client(CreateClientCommand command) {
        this.doctorId = command.doctorId();
        this.name = command.name();
        this.dni = command.dni();
        this.email = command.email();
        this.phone = command.phone();
    }
}
