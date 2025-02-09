package com.estrellaticona.vetcare.appointments.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
