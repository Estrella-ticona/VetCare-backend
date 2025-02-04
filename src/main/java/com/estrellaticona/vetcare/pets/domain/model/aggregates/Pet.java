package com.estrellaticona.vetcare.pets.domain.model.aggregates;

import com.estrellaticona.vetcare.pets.domain.model.commands.CreatePetCommand;
import com.estrellaticona.vetcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pet extends AuditableAbstractAggregateRoot<Pet> {
    @NotNull
    private Long clientId;

    @NotBlank
    private String name;

    @NotBlank
    private String gender;

    @NotNull
    @Column(nullable = false)
    private Integer age;

    public Pet() {
    }

    public Pet(CreatePetCommand command) {
        this.clientId = command.clientId();
        this.name = command.name();
        this.gender = command.gender();
        this.age = command.age();
    }
}
