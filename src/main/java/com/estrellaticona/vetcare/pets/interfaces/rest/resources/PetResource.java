package com.estrellaticona.vetcare.pets.interfaces.rest.resources;

public record PetResource(
        Long id,
        Long clientId,
        String name,
        char gender,
        Integer age,
        String specie,
        String register) {
}
