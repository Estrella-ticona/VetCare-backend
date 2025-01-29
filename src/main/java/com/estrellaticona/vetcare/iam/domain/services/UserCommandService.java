package com.estrellaticona.vetcare.iam.domain.services;

import com.estrellaticona.vetcare.iam.domain.model.commands.SignInCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.SignUpCommand;

public interface UserCommandService {
    String handle(SignUpCommand command);

    String handle(SignInCommand command);
}
