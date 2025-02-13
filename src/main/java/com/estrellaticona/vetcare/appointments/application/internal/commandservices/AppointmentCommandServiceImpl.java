package com.estrellaticona.vetcare.appointments.application.internal.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalClientService;
import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalPetService;
import com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl.ExternalUserService;
import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;
import com.estrellaticona.vetcare.appointments.domain.model.commands.CreateAppointmentCommand;
import com.estrellaticona.vetcare.appointments.domain.model.commands.UpdateAppointmentByIdCommand;
import com.estrellaticona.vetcare.appointments.domain.services.AppointmentCommandService;
import com.estrellaticona.vetcare.appointments.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import io.vavr.Tuple;
import io.vavr.Tuple5;
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
        var appointmentInfo = getAppointmentInfo(command.doctorId(), command.clientId(), command.petId());

        var appointment = new Appointment(command);
        var createdAppointment = clientRepository.save(appointment);

        return Tuple.of(createdAppointment, appointmentInfo._1(), appointmentInfo._2, appointmentInfo._3,
                appointmentInfo._4,
                appointmentInfo._5);
    }

    @Override
    public Tuple6<Appointment, String, String, String, String, String> handle(UpdateAppointmentByIdCommand command) {
        var appointment = clientRepository.findById(command.appointmentId());

        if (appointment.isEmpty())
            throw new RuntimeException("Appointment not found");

        var appointmentInfo = getAppointmentInfo(
                appointment.get().getDoctorId(),
                appointment.get().getClientId(),
                appointment.get().getPetId());

        command.description().ifPresent(appointment.get()::setDescription);
        command.diagnosis().ifPresent(appointment.get()::setDiagnosis);
        command.treatment().ifPresent(appointment.get()::setTreatment);
        command.observations().ifPresent(appointment.get()::setObservations);

        var updatedAppointment = clientRepository.save(appointment.get());

        return Tuple.of(updatedAppointment, appointmentInfo._1(), appointmentInfo._2, appointmentInfo._3,
                appointmentInfo._4,
                appointmentInfo._5);
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