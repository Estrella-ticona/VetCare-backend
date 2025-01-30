package com.estrellaticona.vetcare.iam.interfaces.rest.transform;

import com.estrellaticona.vetcare.iam.domain.model.commands.UpdatePhoneCommand;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdatePhoneResource;

public class UpdatePhoneCommandFromResourceAssembler {
    public static UpdatePhoneCommand toCommandFromResource(Long userId, UpdatePhoneResource resource) {
        return new UpdatePhoneCommand(userId, resource.phone());
    }
}