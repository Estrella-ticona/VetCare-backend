package com.estrellaticona.vetcare.pets.application.internal.queryservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.pets.domain.model.aggregates.Pet;
import com.estrellaticona.vetcare.pets.domain.model.queries.GetAllPetsByClientIdQuery;
import com.estrellaticona.vetcare.pets.domain.services.PetQueryService;
import com.estrellaticona.vetcare.pets.infrastructure.persistence.jpa.repositories.PetRepository;

@Service
public class PetQueryServiceImpl implements PetQueryService {
    @Autowired
    private PetRepository petRepository;

    @Override
    public List<Pet> handle(GetAllPetsByClientIdQuery query) {
        return petRepository.findAllByClientId(query.clientId());
    }
}