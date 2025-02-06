package com.estrellaticona.vetcare.pets.interfaces.rest.transform;

import com.estrellaticona.vetcare.pets.domain.model.commands.DeletePetCommand;
import com.estrellaticona.vetcare.pets.interfaces.rest.resources.DeletePetResource;

public class DeletePetCommandFromResourceAssembler {
    public static DeletePetCommand toCommandFromResource(DeletePetResource resource) {
        return new DeletePetCommand(resource.id());
    }
}
