package com.estrellaticona.vetcare.appointments.domain.services;

import java.util.List;

import com.estrellaticona.vetcare.appointments.domain.model.queries.GetAllAppointmentsQuery;
import com.estrellaticona.vetcare.appointments.domain.model.queries.GetAllHistoriesQuery;
import com.estrellaticona.vetcare.appointments.domain.model.queries.GetHistoryByClientIdQuery;
import com.estrellaticona.vetcare.appointments.domain.model.valueobjects.AppointmentWithInfo;

public interface AppointmentQueryService {
    List<AppointmentWithInfo> handle(GetAllAppointmentsQuery query);

    List<AppointmentWithInfo> handle(GetHistoryByClientIdQuery query);

    List<List<AppointmentWithInfo>> handle(GetAllHistoriesQuery query);
}
