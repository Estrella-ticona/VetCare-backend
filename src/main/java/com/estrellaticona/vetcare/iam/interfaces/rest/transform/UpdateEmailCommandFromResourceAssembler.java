package com.estrellaticona.vetcare.iam.interfaces.rest.transform;

import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateEmailCommand;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdateEmailResource;

public class UpdateEmailCommandFromResourceAssembler {
    public static UpdateEmailCommand toCommandFromResource(Long userId, UpdateEmailResource resource) {
        return new UpdateEmailCommand(userId, resource.email());
    }
}