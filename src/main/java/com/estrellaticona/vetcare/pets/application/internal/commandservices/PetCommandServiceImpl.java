package com.estrellaticona.vetcare.pets.application.internal.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.pets.domain.model.aggregates.Pet;
import com.estrellaticona.vetcare.pets.domain.model.commands.CreatePetCommand;
import com.estrellaticona.vetcare.pets.domain.model.commands.DeletePetCommand;
import com.estrellaticona.vetcare.pets.domain.services.PetCommandService;
import com.estrellaticona.vetcare.pets.infrastructure.persistence.jpa.repositories.PetRepository;

@Service
public class PetCommandServiceImpl implements PetCommandService {
    @Autowired
    private PetRepository clientRepository;

    @Override
    public Pet handle(CreatePetCommand command) {
        var pet = new Pet(command);
        return clientRepository.save(pet);
    }

    @Override
    public void handle(DeletePetCommand command) {
        try {
            clientRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new RuntimeException("Pet not found");
        }
    }
}