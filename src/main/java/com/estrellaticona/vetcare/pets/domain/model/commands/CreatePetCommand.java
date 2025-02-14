package com.estrellaticona.vetcare.pets.domain.model.commands;

import java.time.LocalDate;

public record CreatePetCommand(
        Long clientId,
        String name,
        String specie,
        char gender,
        String breed,
        LocalDate birthDate,
        Float weight) {
}
