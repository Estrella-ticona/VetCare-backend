package com.estrellaticona.vetcare.pets.domain.model.commands;

public record CreatePetCommand(
                Long clientId,
                String name,
                char gender,
                Integer age,
                String specie) {
}
