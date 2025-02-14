package com.estrellaticona.vetcare.appointments.interfaces.rest.transform;

import java.util.List;

import com.estrellaticona.vetcare.appointments.domain.model.valueobjects.AppointmentWithInfo;
import com.estrellaticona.vetcare.appointments.interfaces.rest.resources.HistoryResource;

public class HistoryResourceFromEntityAssembler {
    public static HistoryResource toResourceFromEntity(
            List<AppointmentWithInfo> entity) {
        return new HistoryResource(
                entity.stream()
                        .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                        .toList());
    }
}
