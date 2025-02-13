package com.estrellaticona.vetcare.appointments.domain.services;

import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;
import com.estrellaticona.vetcare.appointments.domain.model.commands.CreateAppointmentCommand;
import com.estrellaticona.vetcare.appointments.domain.model.commands.UpdateAppointmentByIdCommand;

import io.vavr.Tuple6;

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
    Tuple6<Appointment, String, String, String, String, String> handle(CreateAppointmentCommand command);

    Tuple6<Appointment, String, String, String, String, String> handle(UpdateAppointmentByIdCommand command);
}
