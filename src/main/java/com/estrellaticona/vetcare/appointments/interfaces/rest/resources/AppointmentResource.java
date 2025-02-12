package com.estrellaticona.vetcare.appointments.interfaces.rest.resources;

import java.time.LocalDateTime;

public record AppointmentResource(
        Long doctorId,
        String doctorName,
        Long clientId,
        String clientName,
        Long petId,
        String petName,
        String petSpecie,
        String petGender,
        String reason,
        String description,
        String diagnosis,
        String treatment,
        String observations,
        LocalDateTime date) {

}