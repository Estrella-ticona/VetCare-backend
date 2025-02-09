package com.estrellaticona.vetcare.appointments.interfaces.rest.resources;

import java.time.LocalDateTime;

public record CreateAppointmentResource(
        Long clientId,
        Long petId,
        String reason,
        LocalDateTime date) {

}