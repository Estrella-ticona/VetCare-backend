package com.estrellaticona.vetcare.appointments.domain.model.valueobjects;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.estrellaticona.vetcare.appointments.domain.model.aggregates.Appointment;

import io.vavr.Tuple6;
import lombok.Getter;

@Getter
public class AppointmentWithInfo {
    private Long id;
    private Long doctorId;
    private String doctorName;
    private Long clientId;
    private String clientName;
    private Long petId;
    private String petName;
    private String petSpecie;
    private String petGender;
    private String petBreed;
    private LocalDate petBirthDate;
    private Float petWeight;
    private String reason;
    private String description;
    private String diagnosis;
    private String treatment;
    private String observations;
    private LocalDateTime date;

    public AppointmentWithInfo(
            Appointment appointment,
            String doctorName,
            String clientName,
            // name, specie, gender, breed, birthDate, weight
            Tuple6<String, String, String, String, LocalDate, Float> petInfo) {
        this.id = appointment.getId();
        this.doctorId = appointment.getDoctorId();
        this.doctorName = doctorName;
        this.clientId = appointment.getClientId();
        this.clientName = clientName;
        this.petId = appointment.getPetId();
        this.petName = petInfo._1();
        this.petSpecie = petInfo._2();
        this.petGender = petInfo._3();
        this.petBreed = petInfo._4();
        this.petBirthDate = petInfo._5();
        this.petWeight = petInfo._6();
        this.reason = appointment.getReason();
        this.description = appointment.getDescription();
        this.diagnosis = appointment.getDiagnosis();
        this.treatment = appointment.getTreatment();
        this.observations = appointment.getObservations();
        this.date = appointment.getDate();
    }
}
