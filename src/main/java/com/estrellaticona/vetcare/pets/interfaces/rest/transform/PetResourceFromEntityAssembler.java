package com.estrellaticona.vetcare.pets.interfaces.rest.transform;

import java.time.LocalDate;
import java.time.ZoneId;

import com.estrellaticona.vetcare.pets.domain.model.aggregates.Pet;
import com.estrellaticona.vetcare.pets.interfaces.rest.resources.PetResource;

public class PetResourceFromEntityAssembler {
    public static PetResource toResourceFromEntity(Pet entity) {
        var date = entity.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        var age = LocalDate.now().getYear() - entity.getBirthDate().getYear();

        return new PetResource(
                entity.getId(),
                entity.getClientId(),
                entity.getName(),
                entity.getSpecie(),
                entity.getGender(),
                entity.getBreed(),
                entity.getBirthDate(),
                entity.getWeight(),
                age,
                date);
    }
}
