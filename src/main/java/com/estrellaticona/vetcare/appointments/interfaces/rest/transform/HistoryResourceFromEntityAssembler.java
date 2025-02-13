package com.estrellaticona.vetcare.appointments.interfaces.rest.transform;

import java.util.List;

import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;
import com.estrellaticona.vetcare.appointments.interfaces.rest.resources.HistoryResource;

import io.vavr.Tuple6;

public class HistoryResourceFromEntityAssembler {
    public static HistoryResource toResourceFromEntity(
            List<Tuple6<Appointment, String, String, String, String, String>> entity) {
        return new HistoryResource(
                entity.stream()
                        .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                        .toList());
    }
}
