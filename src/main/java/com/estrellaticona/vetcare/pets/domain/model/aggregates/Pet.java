package com.estrellaticona.vetcare.pets.domain.model.aggregates;

import java.time.LocalDate;

import com.estrellaticona.vetcare.pets.domain.model.commands.CreatePetCommand;
import com.estrellaticona.vetcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

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
    private String specie;

    @NotNull
    private char gender;

    @NotBlank
    private String breed;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private Float weight;

    public Pet() {
    }

    public Pet(CreatePetCommand command) {
        this.clientId = command.clientId();
        this.name = command.name();

        if (Character.toUpperCase(command.gender()) != 'M'
                && Character.toUpperCase(command.gender()) != 'F')
            throw new IllegalArgumentException("Gender must be M or F");

        this.gender = Character.toUpperCase(command.gender());
        this.specie = command.specie();
        this.breed = command.breed();
        this.birthDate = command.birthDate();
        this.weight = command.weight();
    }
}
