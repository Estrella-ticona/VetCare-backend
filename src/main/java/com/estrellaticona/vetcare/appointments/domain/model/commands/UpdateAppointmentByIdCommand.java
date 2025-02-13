package com.estrellaticona.vetcare.appointments.domain.model.commands;

import java.util.Optional;

public record UpdateAppointmentByIdCommand(
        Long appointmentId,
        Optional<String> description,
        Optional<String> diagnosis,
        Optional<String> treatment,
        Optional<String> observations) {

}
