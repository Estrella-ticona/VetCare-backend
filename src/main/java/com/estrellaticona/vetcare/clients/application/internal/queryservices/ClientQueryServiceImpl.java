package com.estrellaticona.vetcare.clients.application.internal.queryservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.clients.application.internal.outboundservices.acl.ExternalUserService;
import com.estrellaticona.vetcare.clients.domain.model.aggregates.Client;
import com.estrellaticona.vetcare.clients.domain.model.queries.ExistsByIdQuery;
import com.estrellaticona.vetcare.clients.domain.model.queries.GetAllClientsQuery;
import com.estrellaticona.vetcare.clients.domain.services.ClientQueryService;
import com.estrellaticona.vetcare.clients.infrastructure.persistence.jpa.repositories.ClientRepository;

@Service
public class ClientQueryServiceImpl implements ClientQueryService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ExternalUserService externalUserService;

    @Override
    public List<Pair<Client, String>> handle(GetAllClientsQuery query) {
        var clients = clientRepository.findAll();
        var doctorsName = clients.stream()
                .map(client -> externalUserService.getNameById(client.getDoctorId()))
                .toList();

        return clients.stream()
                .map(client -> Pair.of(client, doctorsName.get(clients.indexOf(client))))
                .toList();
    }

    @Override
    public boolean handle(ExistsByIdQuery query) {
        return clientRepository.existsById(query.clientId());
    }
}