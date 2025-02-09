package com.estrellaticona.vetcare.appointments.interfaces.rest.transform;

import com.estrellaticona.vetcare.appointments.domain.model.commands.CreateAppointmentCommand;
import com.estrellaticona.vetcare.appointments.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource) {
        return new CreateAppointmentCommand(
                resource.clientId(),
                resource.petId(),
                resource.reason(),
                resource.date());
    }
}
