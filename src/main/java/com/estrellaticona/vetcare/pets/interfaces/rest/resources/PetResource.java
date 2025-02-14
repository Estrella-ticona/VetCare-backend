package com.estrellaticona.vetcare.pets.interfaces.rest.resources;

import java.time.LocalDate;

public record PetResource(
                Long id,
                Long clientId,
                String name,
                String specie,
                char gender,
                String breed,
                LocalDate birthDate,
                Float weight,
                Integer age,
                LocalDate registerDate) {
}
