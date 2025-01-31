package com.estrellaticona.vetcare.iam.domain.model.commands;

import java.util.Optional;

public record UpdateUserCommand(
                Long id,
                String email,
                Optional<String> password,
                String name,
                String speciality,
                Integer dni,
                Integer phone) {
        public UpdateUserCommand {
                speciality = speciality.substring(0, 1).toUpperCase()
                                + speciality.substring(1).toLowerCase();
        }
}
