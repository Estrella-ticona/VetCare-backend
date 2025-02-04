package com.estrellaticona.vetcare.pets.interfaces.rest.transform;

import com.estrellaticona.vetcare.pets.domain.model.aggregates.Pet;
import com.estrellaticona.vetcare.pets.interfaces.rest.resources.PetResource;

public class PetResourceFromEntityAssembler {
    public static PetResource toResourceFromEntity(Pet entity) {
        return new PetResource(
                entity.getName(),
                entity.getGender(),
                entity.getAge());
    }
}
