package com.estrellaticona.vetcare.pets.domain.services;

import java.util.List;

import com.estrellaticona.vetcare.pets.domain.model.aggregates.Pet;
import com.estrellaticona.vetcare.pets.domain.model.queries.ExistsByIdQuery;
import com.estrellaticona.vetcare.pets.domain.model.queries.GetAllPetsByClientIdQuery;
import com.estrellaticona.vetcare.pets.domain.model.queries.GetByIdQuery;

public interface PetQueryService {
    List<Pet> handle(GetAllPetsByClientIdQuery query);

    boolean handle(ExistsByIdQuery query);

    Pet handle(GetByIdQuery query);
}
