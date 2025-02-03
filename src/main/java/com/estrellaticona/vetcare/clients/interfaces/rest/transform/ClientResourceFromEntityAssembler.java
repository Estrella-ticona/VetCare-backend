package com.estrellaticona.vetcare.clients.interfaces.rest.transform;

import org.springframework.data.util.Pair;

import com.estrellaticona.vetcare.clients.domain.model.aggregates.Client;
import com.estrellaticona.vetcare.clients.interfaces.rest.resources.ClientResource;

public class ClientResourceFromEntityAssembler {
    public static ClientResource toResourceFromEntity(Pair<Client, String> entity) {
        return new ClientResource(
                entity.getSecond(),
                entity.getFirst().getName(),
                entity.getFirst().getDni(),
                entity.getFirst().getEmail(),
                entity.getFirst().getPhone());
    }
}
