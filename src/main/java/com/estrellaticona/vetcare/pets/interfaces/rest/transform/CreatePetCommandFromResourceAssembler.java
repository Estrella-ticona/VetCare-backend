package com.estrellaticona.vetcare.pets.interfaces.rest.transform;

import com.estrellaticona.vetcare.pets.domain.model.commands.CreatePetCommand;
import com.estrellaticona.vetcare.pets.interfaces.rest.resources.CreatePetResource;

public class CreatePetCommandFromResourceAssembler {
    public static CreatePetCommand toCommandFromResource(CreatePetResource resource) {
        return new CreatePetCommand(
                resource.clientId(),
                resource.name(),
                resource.specie(),
                resource.gender(),
                resource.breed(),
                resource.birthDate(),
                resource.weight());
    }
}
