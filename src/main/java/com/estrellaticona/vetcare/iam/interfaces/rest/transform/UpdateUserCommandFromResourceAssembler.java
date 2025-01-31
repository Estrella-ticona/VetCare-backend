package com.estrellaticona.vetcare.iam.interfaces.rest.transform;

import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateUserCommand;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(Long userId, UpdateUserResource resource) {
        return new UpdateUserCommand(
                userId,
                resource.email(),
                resource.password(),
                resource.name(),
                resource.speciality(),
                resource.dni(),
                resource.phone());
    }
}