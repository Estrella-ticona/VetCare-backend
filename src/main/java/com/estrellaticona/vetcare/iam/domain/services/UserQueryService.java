package com.estrellaticona.vetcare.iam.domain.services;

import java.util.Optional;

import com.estrellaticona.vetcare.iam.domain.model.aggregates.User;
import com.estrellaticona.vetcare.iam.domain.model.queries.GetUserByEmailQuery;

public interface UserQueryService {
    Optional<User> handle(GetUserByEmailQuery query);
}
