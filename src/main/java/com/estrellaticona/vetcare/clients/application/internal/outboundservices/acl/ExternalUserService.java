package com.estrellaticona.vetcare.clients.application.internal.outboundservices.acl;

import org.springframework.beans.factory.annotation.Autowired;

import com.estrellaticona.vetcare.iam.interfaces.acl.UsersContextFacade;

public class ExternalUserService {
    @Autowired
    private UsersContextFacade usersContextFacade;

    public String getNameById(Long doctorId) {
        return usersContextFacade.getNameById(doctorId);
    }
}
