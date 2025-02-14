package com.estrellaticona.vetcare.appointments.application.internal.queryservices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalClientService;
import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalPetService;
import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalUserService;
import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;
import com.estrellaticona.vetcare.appointments.domain.model.queries.GetAllAppointmentsQuery;
import com.estrellaticona.vetcare.appointments.domain.model.queries.GetAllHistoriesQuery;
import com.estrellaticona.vetcare.appointments.domain.model.queries.GetHistoryByClientIdQuery;
import com.estrellaticona.vetcare.appointments.domain.services.AppointmentQueryService;
import com.estrellaticona.vetcare.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import io.vavr.Tuple;
import io.vavr.Tuple5;
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
                    var appointmentInfo = getAppointmentInfo(
                            appointment.getDoctorId(),
                            appointment.getClientId(),
                            appointment.getPetId());

                    return Tuple.of(
                            appointment,
                            appointmentInfo._1(),
                            appointmentInfo._2(),
                            appointmentInfo._3(),
                            appointmentInfo._4(),
                            appointmentInfo._5());
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
                    var appointmentInfo = getAppointmentInfo(
                            appointment.getDoctorId(),
                            appointment.getClientId(),
                            appointment.getPetId());

                    return Tuple.of(
                            appointment,
                            appointmentInfo._1(),
                            appointmentInfo._2(),
                            appointmentInfo._3(),
                            appointmentInfo._4(),
                            appointmentInfo._5());
                })
                .toList();

        return appointments;
    }

    @Override
    public List<List<Tuple6<Appointment, String, String, String, String, String>>> handle(GetAllHistoriesQuery query) {
        var appointmentsWithoutInfoPet = appointmentRepository.findAll();

        var appointments = appointmentsWithoutInfoPet.stream()
                .map(appointment -> {
                    var appointmentInfo = getAppointmentInfo(
                            appointment.getDoctorId(),
                            appointment.getClientId(),
                            appointment.getPetId());

                    return Tuple.of(
                            appointment,
                            appointmentInfo._1(),
                            appointmentInfo._2(),
                            appointmentInfo._3(),
                            appointmentInfo._4(),
                            appointmentInfo._5());
                })
                .collect(Collectors.groupingBy(t -> t._1().getClientId()))
                .values()
                .stream()
                .toList();

        return appointments;
    }

    private Tuple5<String, String, String, String, String> getAppointmentInfo(Long doctorId, Long clientId,
            Long petId) {
        if (!externalClientService.existsById(clientId))
            throw new RuntimeException("Client not found");

        if (!externalPetService.existsById(petId))
            throw new RuntimeException("Pet not found");

        if (!externalPetService.belongToClient(petId, clientId))
            throw new RuntimeException("Pet does not belong to client");

        var doctorName = externalUserService.getNameById(doctorId);
        var clientName = externalClientService.getClientName(clientId);
        var petInfo = externalPetService.getInfoById(petId);

        // doctorName, clientName, petName, petSpecie, petGender
        return Tuple.of(doctorName, clientName, petInfo._1(), petInfo._2(), petInfo._3());
    }
}