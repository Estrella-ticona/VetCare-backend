package com.estrellaticona.vetcare.pets.interfaces.rest.transform;

import java.text.SimpleDateFormat;

import com.estrellaticona.vetcare.pets.domain.model.aggregates.Pet;
import com.estrellaticona.vetcare.pets.interfaces.rest.resources.PetResource;

public class PetResourceFromEntityAssembler {
    public static PetResource toResourceFromEntity(Pet entity) {
        var format = new SimpleDateFormat("dd-MM-yyyy");
        var date = format.format(entity.getCreatedAt());
        return new PetResource(
                entity.getId(),
                entity.getClientId(),
                entity.getName(),
                entity.getGender(),
                entity.getAge(),
                entity.getSpecie(),
                date);
    }
}
