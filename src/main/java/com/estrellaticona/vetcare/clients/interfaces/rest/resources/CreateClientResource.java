package com.estrellaticona.vetcare.clients.interfaces.rest.resources;

public record CreateClientResource(
                String name,
                Integer dni,
                String email,
                Integer phone) {

}
