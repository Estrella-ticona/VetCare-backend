package com.estrellaticona.vetcare.iam.domain.model.commands;

public record SignUpCommand(
        String email,
        String password,
        String name,
        String speciality,
        Integer dni,
        Integer phone) {

}
