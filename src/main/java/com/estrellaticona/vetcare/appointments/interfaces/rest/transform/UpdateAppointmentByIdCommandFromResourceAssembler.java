package com.estrellaticona.vetcare.appointments.interfaces.rest.transform;

import com.estrellaticona.vetcare.appointments.domain.model.commands.UpdateAppointmentByIdCommand;
import com.estrellaticona.vetcare.appointments.interfaces.rest.resources.UpdateAppointmentByIdResource;

public class UpdateAppointmentByIdCommandFromResourceAssembler {
        public static UpdateAppointmentByIdCommand toCommandFromResource(UpdateAppointmentByIdResource resource) {
                return new UpdateAppointmentByIdCommand(
                                resource.id(),
                                resource.description(),
                                resource.diagnosis(),
                                resource.treatment(),
                                resource.observations());
        }
}
