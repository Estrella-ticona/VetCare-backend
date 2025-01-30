package com.estrellaticona.vetcare.iam.interfaces.rest.transform;

import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateDniCommand;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdateDniResource;

public class UpdateDniCommandFromResourceAssembler {
    public static UpdateDniCommand toCommandFromResource(Long userId, UpdateDniResource resource) {
        return new UpdateDniCommand(userId, resource.dni());
    }
}