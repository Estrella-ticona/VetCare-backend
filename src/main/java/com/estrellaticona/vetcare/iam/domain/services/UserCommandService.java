package com.estrellaticona.vetcare.iam.domain.services;

import com.estrellaticona.vetcare.iam.domain.model.aggregates.User;
import com.estrellaticona.vetcare.iam.domain.model.commands.SignInCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.SignUpCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateDniCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateEmailCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateNameCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdatePasswordCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdatePhoneCommand;
import com.estrellaticona.vetcare.iam.domain.model.commands.UpdateSpecialityCommand;

public interface UserCommandService {
    String handle(SignUpCommand command);

    String handle(SignInCommand command);

    User handle(UpdateEmailCommand command);

    User handle(UpdatePasswordCommand command);

    User handle(UpdateNameCommand command);

    User handle(UpdateSpecialityCommand command);

    User handle(UpdateDniCommand command);

    User handle(UpdatePhoneCommand command);
}
