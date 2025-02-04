package com.estrellaticona.vetcare.clients.interfaces.rest.resources;

public record ClientResource(
                Long id,
                String doctorName,
                String name,
                Integer dni,
                String email,
                Integer phone) {

}
