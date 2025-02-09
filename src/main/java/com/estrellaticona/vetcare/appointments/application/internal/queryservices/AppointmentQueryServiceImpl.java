package com.estrellaticona.vetcare.appointments.application.internal.queryservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalPetService;
import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;
import com.estrellaticona.vetcare.appointments.domain.model.queries.GetAllAppointmentsQuery;
import com.estrellaticona.vetcare.appointments.domain.services.AppointmentQueryService;
import com.estrellaticona.vetcare.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import io.vavr.Tuple;
import io.vavr.Tuple4;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ExternalPetService externalPetService;

    @Override
    public List<Tuple4<Appointment, String, String, String>> handle(GetAllAppointmentsQuery query) {
        var appointmentsWithoutInfoPet = appointmentRepository.findAll();

        var appointments = appointmentsWithoutInfoPet.stream()
                .map(appointment -> {
                    var petInfo = externalPetService.getInfoById(appointment.getPetId());
                    return Tuple.of(appointment, petInfo._1(), petInfo._2(), petInfo._3());
                })
                .toList();

        return appointments;
    }
}