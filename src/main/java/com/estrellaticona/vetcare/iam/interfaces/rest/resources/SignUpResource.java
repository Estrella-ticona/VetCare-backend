package com.estrellaticona.vetcare.iam.interfaces.rest.resources;

public record SignUpResource(
        String email,
        String password,
        String name,
        String speciality,
        Integer dni,
        Integer phone) {

}
