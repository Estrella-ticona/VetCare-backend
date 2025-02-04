package com.estrellaticona.vetcare.pets.infrastructure.persistence.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estrellaticona.vetcare.pets.domain.model.aggregates.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByClientId(Long clientId);
}
