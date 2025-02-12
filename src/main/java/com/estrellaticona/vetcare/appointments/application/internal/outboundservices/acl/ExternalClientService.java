package com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.clients.interfaces.acl.ClientsContextFacade;

@Service
public class ExternalClientService {
    @Autowired
    private ClientsContextFacade clientsContextFacade;

    public boolean existsById(Long clientId) {
        return clientsContextFacade.existsById(clientId);
    }

    public String getClientName(Long clientId) {
        return clientsContextFacade.getClientName(clientId);
    }
}
