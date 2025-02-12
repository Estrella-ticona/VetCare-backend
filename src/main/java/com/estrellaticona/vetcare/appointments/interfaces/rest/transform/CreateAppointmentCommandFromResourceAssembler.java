package com.estrellaticona.vetcare.appointments.interfaces.rest.transform;

import com.estrellaticona.vetcare.appointments.domain.model.commands.CreateAppointmentCommand;
import com.estrellaticona.vetcare.appointments.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(Long doctorId, CreateAppointmentResource resource) {
        return new CreateAppointmentCommand(
                doctorId,
                resource.clientId(),
                resource.petId(),
                resource.reason(),
                resource.date());
    }
}
