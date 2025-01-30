package com.estrellaticona.vetcare.iam.domain.model.commands;

public record UpdateSpecialityCommand(Long id, String speciality) {
    public UpdateSpecialityCommand {
        speciality = speciality.substring(0, 1).toUpperCase()
                + speciality.substring(1).toLowerCase();
    }
}
