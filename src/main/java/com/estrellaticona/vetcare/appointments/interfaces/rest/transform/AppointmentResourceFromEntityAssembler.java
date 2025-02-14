package com.estrellaticona.vetcare.appointments.interfaces.rest.transform;

import java.time.LocalDate;
import java.time.Period;

import com.estrellaticona.vetcare.appointments.domain.model.valueobjects.AppointmentWithInfo;
import com.estrellaticona.vetcare.appointments.interfaces.rest.resources.AppointmentResource;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(
            AppointmentWithInfo entity) {
        var age = Period.between(entity.getPetBirthDate(), LocalDate.now()).getYears();

        return new AppointmentResource(
                entity.getId(),
                entity.getDoctorId(),
                entity.getDoctorName(),
                entity.getClientId(),
                entity.getClientName(),
                entity.getPetId(),
                entity.getPetName(),
                entity.getPetSpecie(),
                entity.getPetGender(),
                entity.getPetBreed(),
                entity.getPetBirthDate(),
                entity.getPetWeight(),
                Integer.valueOf(age),
                entity.getReason(),
                entity.getDescription(),
                entity.getDiagnosis(),
                entity.getTreatment(),
                entity.getObservations(),
                entity.getDate());
    }
}
