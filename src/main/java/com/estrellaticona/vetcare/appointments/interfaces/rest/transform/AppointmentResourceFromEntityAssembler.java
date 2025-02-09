package com.estrellaticona.vetcare.appointments.interfaces.rest.transform;

import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;
import com.estrellaticona.vetcare.appointments.interfaces.rest.resources.AppointmentResource;

import io.vavr.Tuple4;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(Tuple4<Appointment, String, String, String> entity) {
        var appointment = entity._1;
        var petName = entity._2;
        var petSpecie = entity._3;
        var petGender = entity._4;

        return new AppointmentResource(
                appointment.getClientId(),
                appointment.getPetId(),
                petName,
                petSpecie,
                petGender,
                appointment.getReason(),
                appointment.getDate());
    }
}
