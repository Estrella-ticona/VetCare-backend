package com.estrellaticona.vetcare.appointments.domain.model.commands;

import java.time.LocalDateTime;

public record CreateAppointmentCommand(
        Long clientId,
        Long petId,
        String reason,
        LocalDateTime date) {
}