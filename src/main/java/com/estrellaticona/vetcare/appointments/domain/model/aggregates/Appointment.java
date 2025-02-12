package com.estrellaticona.vetcare.appointments.domain.model.aggregates;

import java.time.LocalDateTime;
import com.estrellaticona.vetcare.appointments.domain.model.commands.CreateAppointmentCommand;
import com.estrellaticona.vetcare.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {
    @NotNull
    private Long doctorId;

    @NotNull
    private Long clientId;

    @NotNull
    private Long petId;

    @NotBlank
    private String reason;

    private String description;

    private String diagnosis;

    private String treatment;

    private String observations;

    @NotNull
    private LocalDateTime date;

    public Appointment() {
    }

    public Appointment(CreateAppointmentCommand command) {
        this.doctorId = command.doctorId();
        this.clientId = command.clientId();
        this.petId = command.petId();
        this.reason = command.reason();
        this.date = command.date();
        this.description = "";
        this.diagnosis = "";
        this.treatment = "";
        this.observations = "";
    }
}
