package com.estrellaticona.vetcare.iam.domain.services;

import com.estrellaticona.vetcare.iam.domain.model.aggregates.User;
import com.estrellaticona.vetcare.iam.domain.model.queries.GetUserByEmailQuery;
import com.estrellaticona.vetcare.iam.domain.model.queries.GetUserByIdQuery;

public interface UserQueryService {
    User handle(GetUserByIdQuery query);

    User handle(GetUserByEmailQuery query);
}
