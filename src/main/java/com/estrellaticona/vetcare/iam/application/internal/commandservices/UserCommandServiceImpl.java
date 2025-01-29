package com.estrellaticona.vetcare.iam.application.internal.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.iam.application.internal.outboundservices.hashing.HashingService;
import com.estrellaticona.vetcare.iam.application.internal.outboundservices.tokens.TokenService;
import com.estrellaticona.vetcare.iam.domain.model.aggregates.User;
import com.estrellaticona.vetcare.iam.domain.model.commands.SignInCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.SignUpCommand;
import com.estrellaticona.vetcare.iam.domain.services.UserCommandService;
import com.estrellaticona.vetcare.iam.infrastructure.persistence.jpa.repositories.UserRepository;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private HashingService hashingService;

    @Override
    public String handle(SignUpCommand command) {
        var hashedPassword = hashingService.encode(command.password());
        var commandWithHashedPassword = new SignUpCommand(command.email(), hashedPassword);
        var user = new User(commandWithHashedPassword);

        if (userRepository.existsByEmail(user.getEmail()))
            throw new RuntimeException("Email already in use");

        userRepository.save(user);

        return tokenService.generateToken(user.getEmail());
    }

    @Override
    public String handle(SignInCommand command) {
        var user = userRepository.findByEmail(command.email());

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");

        return tokenService.generateToken(user.get().getEmail());
    }

}