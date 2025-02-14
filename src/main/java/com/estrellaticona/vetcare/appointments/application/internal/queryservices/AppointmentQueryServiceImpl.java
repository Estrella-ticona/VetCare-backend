package com.estrellaticona.vetcare.appointments.application.internal.queryservices;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalClientService;
import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalPetService;
import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalUserService;
import com.estrellaticona.vetcare.appointments.domain.model.queries.GetAllAppointmentsQuery;
import com.estrellaticona.vetcare.appointments.domain.model.queries.GetAllHistoriesQuery;
import com.estrellaticona.vetcare.appointments.domain.model.queries.GetHistoryByClientIdQuery;
import com.estrellaticona.vetcare.appointments.domain.model.valueobjects.AppointmentWithInfo;
import com.estrellaticona.vetcare.appointments.domain.services.AppointmentQueryService;
import com.estrellaticona.vetcare.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import io.vavr.Tuple;
import io.vavr.Tuple3;
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
    public List<AppointmentWithInfo> handle(GetAllAppointmentsQuery query) {
        var appointmentsWithoutInfoPet = appointmentRepository.findAll();

        var appointments = appointmentsWithoutInfoPet.stream()
                .map(appointment -> {
                    var appointmentInfo = getAppointmentInfo(
                            appointment.getDoctorId(),
                            appointment.getClientId(),
                            appointment.getPetId());

                    var appointmentWithInfo = new AppointmentWithInfo(
                            appointment,
                            appointmentInfo._1(),
                            appointmentInfo._2(),
                            appointmentInfo._3());

                    return appointmentWithInfo;
                })
                .toList();

        return appointments;
    }

    @Override
    public List<AppointmentWithInfo> handle(
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

                    var appointmentWithInfo = new AppointmentWithInfo(
                            appointment,
                            appointmentInfo._1(),
                            appointmentInfo._2(),
                            appointmentInfo._3());

                    return appointmentWithInfo;
                })
                .toList();

        return appointments;
    }

    @Override
    public List<List<AppointmentWithInfo>> handle(GetAllHistoriesQuery query) {
        var appointmentsWithoutInfoPet = appointmentRepository.findAll();

        var appointments = appointmentsWithoutInfoPet.stream()
                .map(appointment -> {
                    var appointmentInfo = getAppointmentInfo(
                            appointment.getDoctorId(),
                            appointment.getClientId(),
                            appointment.getPetId());

                    var appointmentWithInfo = new AppointmentWithInfo(
                            appointment,
                            appointmentInfo._1(),
                            appointmentInfo._2(),
                            appointmentInfo._3());

                    return appointmentWithInfo;
                })
                .collect(Collectors.groupingBy(t -> t.getClientId()))
                .values()
                .stream()
                .toList();

        return appointments;
    }

    private Tuple3<String, String, Tuple6<String, String, String, String, LocalDate, Float>> getAppointmentInfo(
            Long doctorId, Long clientId,
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
        return Tuple.of(doctorName, clientName, petInfo);
    }
}