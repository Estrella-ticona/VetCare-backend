package com.estrellaticona.vetcare.iam.interfaces.rest.transform;

import com.estrellaticona.vetcare.iam.domain.model.commands.UpdatePasswordCommand;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdatePasswordResource;

public class UpdatePasswordCommandFromResourceAssembler {
    public static UpdatePasswordCommand toCommandFromResource(Long userId, UpdatePasswordResource resource) {
        return new UpdatePasswordCommand(userId, resource.password());
    }
}
