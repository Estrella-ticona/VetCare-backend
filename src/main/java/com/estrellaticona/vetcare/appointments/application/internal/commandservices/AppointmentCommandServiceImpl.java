package com.estrellaticona.vetcare.appointments.application.internal.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalClientService;
import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalPetService;
import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalUserService;
import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;
import com.estrellaticona.vetcare.appointments.domain.model.commands.CreateAppointmentCommand;
import com.estrellaticona.vetcare.appointments.domain.services.AppointmentCommandService;
import com.estrellaticona.vetcare.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import io.vavr.Tuple;
import io.vavr.Tuple6;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {
    @Autowired
    private AppointmentRepository clientRepository;

    @Autowired
    private ExternalClientService externalClientService;

    @Autowired
    private ExternalPetService externalPetService;

    @Autowired
    @Qualifier("externalUserServiceAppointments")
    private ExternalUserService externalUserService;

    @Override
    public Tuple6<Appointment, String, String, String, String, String> handle(CreateAppointmentCommand command) {
        if (!externalClientService.existsById(command.clientId()))
            throw new RuntimeException("Client not found");

        if (!externalPetService.existsById(command.petId()))
            throw new RuntimeException("Pet not found");

        if (!externalPetService.belongToClient(command.petId(), command.clientId()))
            throw new RuntimeException("Pet does not belong to client");

        var appointment = new Appointment(command);
        var createdAppointment = clientRepository.save(appointment);

        var doctorName = externalUserService.getNameById(command.doctorId());
        var clientName = externalClientService.getClientName(command.clientId());
        var petInfo = externalPetService.getInfoById(command.petId());

        // appointment, clientName, petName, petSpecie, petGender
        return Tuple.of(createdAppointment, doctorName, clientName, petInfo._1(), petInfo._2(), petInfo._3());
    }

}