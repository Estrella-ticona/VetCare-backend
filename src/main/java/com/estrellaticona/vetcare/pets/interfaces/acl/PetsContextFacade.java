package com.estrellaticona.vetcare.pets.interfaces.acl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.pets.domain.model.queries.ExistsByIdQuery;
import com.estrellaticona.vetcare.pets.domain.model.queries.GetByIdQuery;
import com.estrellaticona.vetcare.pets.domain.services.PetQueryService;

import io.vavr.Tuple;
import io.vavr.Tuple6;

@Service
public class PetsContextFacade {
    @Autowired
    private PetQueryService petQueryService;

    public boolean existsById(Long clientId) {
        var query = new ExistsByIdQuery(clientId);

        return petQueryService.handle(query);
    }

    // name, specie, gender, breed, birthDate, weight
    public Tuple6<String, String, String, String, LocalDate, Float> getInfoById(Long petId) {
        var query = new GetByIdQuery(petId);
        var pet = petQueryService.handle(query);

        return Tuple.of(
                pet.getName(),
                pet.getSpecie(),
                String.valueOf(pet.getGender()),
                pet.getBreed(),
                pet.getBirthDate(),
                pet.getWeight());
    }

    public boolean belongToClient(Long petId, Long clientId) {
        var query = new GetByIdQuery(petId);
        var pet = petQueryService.handle(query);

        return pet.getClientId().equals(clientId);
    }
}