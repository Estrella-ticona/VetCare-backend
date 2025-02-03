package com.estrellaticona.vetcare.clients.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estrellaticona.vetcare.clients.domain.model.aggregates.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
