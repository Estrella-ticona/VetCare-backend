package com.estrellaticona.vetcare.clients.domain.model.commands;

public record CreateClientCommand(
        Long doctorId,
        String name,
        Integer dni,
        String email,
        Integer phone) {
}
