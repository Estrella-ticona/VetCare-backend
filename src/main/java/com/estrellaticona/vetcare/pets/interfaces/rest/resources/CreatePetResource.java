package com.estrellaticona.vetcare.pets.interfaces.rest.resources;

import java.time.LocalDate;

public record CreatePetResource(
                Long clientId,
                String name,
                String specie,
                char gender,
                String breed,
                LocalDate birthDate,
                Float weight) {

}
