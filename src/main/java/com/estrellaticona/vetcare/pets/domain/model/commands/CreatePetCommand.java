package com.estrellaticona.vetcare.pets.domain.model.commands;

public record CreatePetCommand(
                Long clientId,
                String name,
                String gender,
                Integer age) {
}
