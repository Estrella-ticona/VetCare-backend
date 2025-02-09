package com.estrellaticona.vetcare.appointments.interfaces.rest.resources;

import java.time.LocalDateTime;

public record AppointmentResource(
                Long clientId,
                Long petId,
                String petName,
                String petSpecie,
                String petGender,
                String reason,
                LocalDateTime date) {

}