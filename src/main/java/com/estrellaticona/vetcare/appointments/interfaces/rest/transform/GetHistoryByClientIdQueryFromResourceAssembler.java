package com.estrellaticona.vetcare.appointments.interfaces.rest.transform;

import com.estrellaticona.vetcare.appointments.domain.model.queries.GetHistoryByClientIdQuery;
import com.estrellaticona.vetcare.appointments.interfaces.rest.resources.GetHistoryByClientIdResource;

public class GetHistoryByClientIdQueryFromResourceAssembler {
    public static GetHistoryByClientIdQuery toQueryFromResource(GetHistoryByClientIdResource resource) {
        return new GetHistoryByClientIdQuery(resource.clientId());
    }
}
