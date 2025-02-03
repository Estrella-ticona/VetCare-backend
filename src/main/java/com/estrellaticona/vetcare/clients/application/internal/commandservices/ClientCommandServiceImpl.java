package com.estrellaticona.vetcare.clients.application.internal.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.clients.application.internal.outboundservices.acl.ExternalUserService;
import com.estrellaticona.vetcare.clients.domain.model.aggregates.Client;
import com.estrellaticona.vetcare.clients.domain.model.commands.CreateClientCommand;
import com.estrellaticona.vetcare.clients.domain.services.ClientCommandService;
import com.estrellaticona.vetcare.clients.infrastructure.persistence.jpa.repositories.ClientRepository;

@Service
public class ClientCommandServiceImpl implements ClientCommandService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ExternalUserService externalUserService;

    @Override
    public Pair<Client, String> handle(CreateClientCommand command) {
        var doctorName = externalUserService.getNameById(command.doctorId());

        var client = new Client(command);
        var createdClient = clientRepository.save(client);

        return Pair.of(createdClient, doctorName);
    }

}