package com.estrellaticona.vetcare.appointments.domain.services;

import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;
import com.estrellaticona.vetcare.appointments.domain.model.commands.CreateAppointmentCommand;

import io.vavr.Tuple4;

public interface AppointmentCommandService {
    /**
     * Returns the created appointment along with the pet's name, species, and
     * gender.
     * 
     * @return a Tuple containing the created appointment, pet name, pet species,
     *         and pet gender
     */
    Tuple4<Appointment, String, String, String> handle(CreateAppointmentCommand command);
}
