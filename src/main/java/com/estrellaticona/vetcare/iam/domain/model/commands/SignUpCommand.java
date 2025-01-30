package com.estrellaticona.vetcare.iam.domain.model.commands;

public record SignUpCommand(
                String email,
                String password,
                String name,
                String speciality,
                Integer dni,
                Integer phone) {
        public SignUpCommand {
                speciality = speciality.substring(0, 1).toUpperCase()
                                + speciality.substring(1).toLowerCase();
        }
}
