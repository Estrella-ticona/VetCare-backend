package com.estrellaticona.vetcare.appointments.domain.services;

import java.util.List;

import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;
import com.estrellaticona.vetcare.appointments.domain.model.queries.GetAllAppointmentsQuery;

import io.vavr.Tuple6;

public interface AppointmentQueryService {
    List<Tuple6<Appointment, String, String, String, String, String>> handle(GetAllAppointmentsQuery query);
}
