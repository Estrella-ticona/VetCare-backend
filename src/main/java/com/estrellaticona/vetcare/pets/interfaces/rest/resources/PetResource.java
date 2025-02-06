package com.estrellaticona.vetcare.pets.interfaces.rest.resources;

public record PetResource(
                Long id,
                String name,
                char gender,
                Integer age) {
}
