package com.estrellaticona.vetcare.appointments.domain.services;

import com.estrellaticona.vetcare.appointments.domain.model.commands.CreateAppointmentCommand;
import com.estrellaticona.vetcare.appointments.domain.model.commands.UpdateAppointmentByIdCommand;
import com.estrellaticona.vetcare.appointments.domain.model.valueobjects.AppointmentWithInfo;

public interface AppointmentCommandService {
    /**
     * Returns the created appointment along with the pet's name, species, and
     * gender.
     * 
     * @return a Tuple containing the created appointment, doctorName, client name,
     *         pet name,
     *         pet species,
     *         and pet gender
     */
    AppointmentWithInfo handle(CreateAppointmentCommand command);

    AppointmentWithInfo handle(UpdateAppointmentByIdCommand command);
}
