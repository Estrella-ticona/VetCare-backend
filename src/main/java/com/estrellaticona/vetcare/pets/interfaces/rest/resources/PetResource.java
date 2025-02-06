package com.estrellaticona.vetcare.pets.interfaces.rest.resources;

import java.util.Date;

public record PetResource(
                Long id,
                Long clientId,
                String name,
                char gender,
                Integer age,
                String specie,
                Date register) {
}
