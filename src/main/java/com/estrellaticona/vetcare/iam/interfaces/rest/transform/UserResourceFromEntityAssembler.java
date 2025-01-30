package com.estrellaticona.vetcare.iam.interfaces.rest.transform;

import com.estrellaticona.vetcare.iam.domain.model.aggregates.User;
import com.estrellaticona.vetcare.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getEmail(),
                entity.getName(),
                entity.getSpeciality(),
                entity.getDni(),
                entity.getPhone());
    }
}