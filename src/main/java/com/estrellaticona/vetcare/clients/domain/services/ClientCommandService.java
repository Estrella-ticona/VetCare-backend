package com.estrellaticona.vetcare.clients.domain.services;

import org.springframework.data.util.Pair;

import com.estrellaticona.vetcare.clients.domain.model.aggregates.Client;
import com.estrellaticona.vetcare.clients.domain.model.commands.CreateClientCommand;

public interface ClientCommandService {
    Pair<Client, String> handle(CreateClientCommand command);
}
