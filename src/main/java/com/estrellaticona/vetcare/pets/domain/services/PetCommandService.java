package com.estrellaticona.vetcare.pets.domain.services;

import com.estrellaticona.vetcare.pets.domain.model.aggregates.Pet;
import com.estrellaticona.vetcare.pets.domain.model.commands.CreatePetCommand;

public interface PetCommandService {
    Pet handle(CreatePetCommand command);
}
