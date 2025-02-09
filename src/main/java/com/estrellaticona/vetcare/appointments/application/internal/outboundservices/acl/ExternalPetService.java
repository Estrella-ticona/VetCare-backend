package com.estrellaticona.vetcare.appointments.application.internal.outboundservices.acl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estrellaticona.vetcare.pets.interfaces.acl.PetsContextFacade;

import io.vavr.Tuple3;

@Service
public class ExternalPetService {
    @Autowired
    private PetsContextFacade petsContextFacade;

    public boolean existsById(Long petId) {
        return petsContextFacade.existsById(petId);
    }

    /**
     * @return a Tuple containing the created appointment, pet name, pet species,
     *         and pet gender
     */
    public Tuple3<String, String, String> getInfoById(Long petId) {
        return petsContextFacade.getInfoById(petId);
    }

    public boolean belongToClient(Long petId, Long clientId) {
        return petsContextFacade.belongToClient(petId, clientId);
    }
}
