package com.estrellaticona.vetcare.iam.interfaces.rest.transform;

import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateNameCommand;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdateNameResource;

public class UpdateNameCommandFromResourceAssembler {
    public static UpdateNameCommand toCommandFromResource(Long userId, UpdateNameResource resource) {
        return new UpdateNameCommand(userId, resource.name());
    }
}