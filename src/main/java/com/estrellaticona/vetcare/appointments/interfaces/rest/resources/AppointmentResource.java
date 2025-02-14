package com.estrellaticona.vetcare.appointments.interfaces.rest.resources;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AppointmentResource(
        Long id,
        Long doctorId,
        String doctorName,
        Long clientId,
        String clientName,
        Long petId,
        String petName,
        String petSpecie,
        String petGender,
        String petBreed,
        LocalDate petBirthDate,
        Float petWeight,
        Integer petAge,
        String reason,
        String description,
        String diagnosis,
        String treatment,
        String observations,
        LocalDateTime date) {

}