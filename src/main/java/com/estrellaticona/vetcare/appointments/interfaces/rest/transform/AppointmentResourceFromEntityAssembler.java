package com.estrellaticona.vetcare.appointments.interfaces.rest.transform;

import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;
import com.estrellaticona.vetcare.appointments.interfaces.rest.resources.AppointmentResource;

import io.vavr.Tuple6;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(
            Tuple6<Appointment, String, String, String, String, String> entity) {
        var appointment = entity._1;
        var doctorName = entity._2;
        var clientName = entity._3;
        var petName = entity._4;
        var petSpecie = entity._5;
        var petGender = entity._6;

        return new AppointmentResource(
                appointment.getId(),
                appointment.getDoctorId(),
                doctorName,
                appointment.getClientId(),
                clientName,
                appointment.getPetId(),
                petName,
                petSpecie,
                petGender,
                appointment.getReason(),
                appointment.getDescription(),
                appointment.getDiagnosis(),
                appointment.getTreatment(),
                appointment.getObservations(),
                appointment.getDate());
    }
}
