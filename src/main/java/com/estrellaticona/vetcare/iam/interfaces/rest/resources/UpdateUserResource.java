package com.estrellaticona.vetcare.iam.interfaces.rest.resources;

import java.util.Optional;

public record UpdateUserResource(
                String email,
                Optional<String> password,
                String name,
                String speciality,
                Integer dni,
                Integer phone) {

}
