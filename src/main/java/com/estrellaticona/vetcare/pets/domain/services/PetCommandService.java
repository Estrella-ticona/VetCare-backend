package com.estrellaticona.vetcare.pets.domain.services;

import com.estrellaticona.vetcare.pets.domain.model.aggregates.Pet;
import com.estrellaticona.vetcare.pets.domain.model.commands.CreatePetCommand;
import com.estrellaticona.vetcare.pets.domain.model.commands.DeletePetCommand;

public interface PetCommandService {
    Pet handle(CreatePetCommand command);

    void handle(DeletePetCommand command);
}
