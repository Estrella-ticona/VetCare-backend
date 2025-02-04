package com.estrellaticona.vetcare.pets.interfaces.rest.transform;

import com.estrellaticona.vetcare.pets.domain.model.queries.GetAllPetsByClientIdQuery;
import com.estrellaticona.vetcare.pets.interfaces.rest.resources.GetAllPetsByClientIdResource;

public class GetAllPetsByClientIdQueryFromResourceAssembler {
    public static GetAllPetsByClientIdQuery toQueryFromResource(GetAllPetsByClientIdResource resource) {
        return new GetAllPetsByClientIdQuery(resource.clientId());
    }
}
