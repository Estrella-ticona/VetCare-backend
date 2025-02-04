package com.estrellaticona.vetcare.pets.domain.services;

import java.util.List;

import com.estrellaticona.vetcare.pets.domain.model.aggregates.Pet;
import com.estrellaticona.vetcare.pets.domain.model.queries.GetAllPetsByClientIdQuery;

public interface PetQueryService {
    List<Pet> handle(GetAllPetsByClientIdQuery query);
}
