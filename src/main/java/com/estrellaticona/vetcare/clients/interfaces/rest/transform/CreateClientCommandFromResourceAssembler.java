package com.estrellaticona.vetcare.clients.interfaces.rest.transform;

import com.estrellaticona.vetcare.clients.domain.model.commands.CreateClientCommand;
import com.estrellaticona.vetcare.clients.interfaces.rest.resources.CreateClientResource;

public class CreateClientCommandFromResourceAssembler {
    public static CreateClientCommand toCommandFromResource(Long doctorId, CreateClientResource resource) {
        return new CreateClientCommand(
                doctorId,
                resource.name(),
                resource.dni(),
                resource.email(),
                resource.phone());
    }
}
