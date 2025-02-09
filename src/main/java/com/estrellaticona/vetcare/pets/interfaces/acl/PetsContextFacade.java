package com.estrellaticona.vetcare.pets.interfaces.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.pets.domain.model.queries.ExistsByIdQuery;
import com.estrellaticona.vetcare.pets.domain.model.queries.GetByIdQuery;
import com.estrellaticona.vetcare.pets.domain.services.PetQueryService;

import io.vavr.Tuple;
import io.vavr.Tuple3;

@Service
public class PetsContextFacade {
    @Autowired
    private PetQueryService petQueryService;

    public boolean existsById(Long clientId) {
        var query = new ExistsByIdQuery(clientId);

        return petQueryService.handle(query);
    }

    public Tuple3<String, String, String> getInfoById(Long petId) {
        var query = new GetByIdQuery(petId);
        var pet = petQueryService.handle(query);

        return Tuple.of(pet.getName(), pet.getSpecie(), String.valueOf(pet.getGender()));
    }

    public boolean belongToClient(Long petId, Long clientId) {
        var query = new GetByIdQuery(petId);
        var pet = petQueryService.handle(query);

        return pet.getClientId().equals(clientId);
    }
}