package com.estrellaticona.vetcare.clients.domain.services;

import java.util.List;

import org.springframework.data.util.Pair;

import com.estrellaticona.vetcare.clients.domain.model.aggregates.Client;
import com.estrellaticona.vetcare.clients.domain.model.queries.GetAllClientsQuery;

public interface ClientQueryService {
    List<Pair<Client, String>> handle(GetAllClientsQuery query);
}
