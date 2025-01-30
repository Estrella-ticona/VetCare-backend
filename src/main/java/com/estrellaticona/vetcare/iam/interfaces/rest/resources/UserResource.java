package com.estrellaticona.vetcare.iam.interfaces.rest.resources;

public record UserResource(
        String email,
        String name,
        String speciality,
        Integer dni,
        Integer phone) {
}
