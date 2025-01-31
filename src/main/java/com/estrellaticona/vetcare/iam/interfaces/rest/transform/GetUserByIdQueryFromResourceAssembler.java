package com.estrellaticona.vetcare.iam.interfaces.rest.transform;

import com.estrellaticona.vetcare.iam.domain.model.queries.GetUserByIdQuery;

public class GetUserByIdQueryFromResourceAssembler {
    public static GetUserByIdQuery toQueryFromResource(Long userId) {
        return new GetUserByIdQuery(userId);
    }
}
