package com.estrellaticona.vetcare.appointments.application.internal.queryservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalClientService;
import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalPetService;
import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalUserService;
import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;
import com.estrellaticona.vetcare.appointments.domain.model.queries.GetAllAppointmentsQuery;
import com.estrellaticona.vetcare.appointments.domain.model.queries.GetHistoryByClientIdQuery;
import com.estrellaticona.vetcare.appointments.domain.services.AppointmentQueryService;
import com.estrellaticona.vetcare.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import io.vavr.Tuple;
import io.vavr.Tuple6;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ExternalPetService externalPetService;

    @Autowired
    private ExternalClientService externalClientService;

    @Autowired
    @Qualifier("externalUserServiceAppointments")
    private ExternalUserService externalUserService;

    @Override
    public List<Tuple6<Appointment, String, String, String, String, String>> handle(GetAllAppointmentsQuery query) {
        var appointmentsWithoutInfoPet = appointmentRepository.findAll();

        var appointments = appointmentsWithoutInfoPet.stream()
                .map(appointment -> {
                    var doctorName = externalUserService.getNameById(appointment.getDoctorId());
                    var clientName = externalClientService.getClientName(appointment.getClientId());
                    var petInfo = externalPetService.getInfoById(appointment.getPetId());
                    return Tuple.of(appointment, doctorName, clientName, petInfo._1(), petInfo._2(), petInfo._3());
                })
                .toList();

        return appointments;
    }

    @Override
    public List<Tuple6<Appointment, String, String, String, String, String>> handle(
            GetHistoryByClientIdQuery query) {
        var appointmentsWithoutInfoPet = appointmentRepository.findByClientId(query.clientId());

        if (appointmentsWithoutInfoPet.isEmpty())
            throw new IllegalArgumentException("No appointments found for client with id " + query.clientId());

        var appointments = appointmentsWithoutInfoPet.stream()
                .map(appointment -> {
                    var doctorName = externalUserService.getNameById(appointment.getDoctorId());
                    var clientName = externalClientService.getClientName(appointment.getClientId());
                    var petInfo = externalPetService.getInfoById(appointment.getPetId());
                    return Tuple.of(appointment, doctorName, clientName, petInfo._1(), petInfo._2(), petInfo._3());
                })
                .toList();

        return appointments;
    }
}