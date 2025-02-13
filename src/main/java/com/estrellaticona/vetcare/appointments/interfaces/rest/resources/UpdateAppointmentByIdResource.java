package com.estrellaticona.vetcare.appointments.interfaces.rest.resources;

import java.util.Optional;

public record UpdateAppointmentByIdResource(
                Long id,
                Optional<String> description,
                Optional<String> diagnosis,
                Optional<String> treatment,
                Optional<String> observations) {

}