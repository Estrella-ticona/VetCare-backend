package com.estrellaticona.vetcare.iam.application.internal.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.iam.application.internal.outboundservices.hashing.HashingService;
import com.estrellaticona.vetcare.iam.application.internal.outboundservices.tokens.TokenService;
import com.estrellaticona.vetcare.iam.domain.model.aggregates.User;
import com.estrellaticona.vetcare.iam.domain.model.commands.SignInCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.SignUpCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateDniCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateEmailCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateNameCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdatePasswordCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdatePhoneCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateSpecialityCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateUserCommand;
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
        var commandWithHashedPassword = new SignUpCommand(
                command.email(),
                hashedPassword,
                command.name(),
                command.speciality(),
                command.dni(),
                command.phone());

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

    @Override
    public User handle(UpdateUserCommand command) {
        var user = userRepository.findById(command.id());

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        if (command.password().isPresent() && !command.password().get().isEmpty()) {
            var hashedPassword = hashingService.encode(command.password().get());
            user.get().setPassword(hashedPassword);
        }

        user.get().setName(command.name());
        user.get().setEmail(command.email());
        user.get().setSpeciality(command.speciality());
        user.get().setDni(command.dni());
        user.get().setPhone(command.phone());

        return userRepository.save(user.get());
    }

    @Override
    public User handle(UpdateEmailCommand command) {
        var user = userRepository.findById(command.id());

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        user.get().setEmail(command.email());

        return userRepository.save(user.get());
    }

    @Override
    public User handle(UpdatePasswordCommand command) {
        var user = userRepository.findById(command.id());

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        var hashedPassword = hashingService.encode(command.password());

        user.get().setPassword(hashedPassword);

        return userRepository.save(user.get());
    }

    @Override
    public User handle(UpdateNameCommand command) {
        var user = userRepository.findById(command.id());

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        user.get().setName(command.name());

        return userRepository.save(user.get());
    }

    @Override
    public User handle(UpdateSpecialityCommand command) {
        var user = userRepository.findById(command.id());

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        user.get().setSpeciality(command.speciality());

        return userRepository.save(user.get());
    }

    @Override
    public User handle(UpdateDniCommand command) {
        var user = userRepository.findById(command.id());

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        user.get().setDni(command.dni());

        return userRepository.save(user.get());
    }

    @Override
    public User handle(UpdatePhoneCommand command) {
        var user = userRepository.findById(command.id());

        if (user.isEmpty())
            throw new RuntimeException("User not found");

        user.get().setPhone(command.phone());

        return userRepository.save(user.get());
    }

}