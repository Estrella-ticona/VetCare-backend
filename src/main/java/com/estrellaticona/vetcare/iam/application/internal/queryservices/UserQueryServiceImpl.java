package com.estrellaticona.vetcare.iam.application.internal.queryservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.iam.domain.model.aggregates.User;
import com.estrellaticona.vetcare.iam.domain.model.queries.GetUserByEmailQuery;
import com.estrellaticona.vetcare.iam.domain.model.queries.GetUserByIdQuery;
import com.estrellaticona.vetcare.iam.domain.services.UserQueryService;
import com.estrellaticona.vetcare.iam.infrastructure.persistence.jpa.repositories.UserRepository;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User handle(GetUserByIdQuery query) {
        var user = userRepository.findById(query.id());

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        return user.get();
    }

    @Override
    public User handle(GetUserByEmailQuery query) {
        var user = userRepository.findByEmail(query.email());

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        return user.get();
    }
}