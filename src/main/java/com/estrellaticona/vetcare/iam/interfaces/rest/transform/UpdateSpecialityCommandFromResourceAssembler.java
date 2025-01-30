package com.estrellaticona.vetcare.iam.interfaces.rest.transform;

import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateSpecialityCommand;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UpdateSpecialityResource;

public class UpdateSpecialityCommandFromResourceAssembler {
    public static UpdateSpecialityCommand toCommandFromResource(Long userId, UpdateSpecialityResource resource) {
        return new UpdateSpecialityCommand(userId, resource.speciality());
    }
}