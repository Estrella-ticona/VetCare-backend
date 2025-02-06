package com.estrellaticona.vetcare.pets.interfaces.rest.resources;

public record CreatePetResource(
        Long clientId,
        String name,
        char gender,
        Integer age,
        String specie) {

}
