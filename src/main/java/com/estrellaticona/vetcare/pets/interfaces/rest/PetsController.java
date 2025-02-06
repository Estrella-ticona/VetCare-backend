package com.estrellaticona.vetcare.pets.interfaces.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estrellaticona.vetcare.pets.domain.services.PetCommandService;
import com.estrellaticona.vetcare.pets.domain.services.PetQueryService;
import com.estrellaticona.vetcare.pets.interfaces.rest.resources.CreatePetResource;
import com.estrellaticona.vetcare.pets.interfaces.rest.resources.DeletePetResource;
import com.estrellaticona.vetcare.pets.interfaces.rest.resources.GetAllPetsByClientIdResource;
import com.estrellaticona.vetcare.pets.interfaces.rest.transform.CreatePetCommandFromResourceAssembler;
import com.estrellaticona.vetcare.pets.interfaces.rest.transform.DeletePetCommandFromResourceAssembler;
import com.estrellaticona.vetcare.pets.interfaces.rest.transform.GetAllPetsByClientIdQueryFromResourceAssembler;
import com.estrellaticona.vetcare.pets.interfaces.rest.transform.PetResourceFromEntityAssembler;

@RestController
@RequestMapping("/pets")
public class PetsController {
    @Autowired
    private PetCommandService petCommandService;

    @Autowired
    private PetQueryService petQueryService;

    @PostMapping("/create")
    public ResponseEntity<Object> createPet(@RequestBody CreatePetResource resource) {
        try {
            var command = CreatePetCommandFromResourceAssembler.toCommandFromResource(resource);

            var pet = petCommandService.handle(command);

            var petResource = PetResourceFromEntityAssembler.toResourceFromEntity(pet);

            return ResponseEntity.ok(petResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> getPetsByClientId(@RequestBody GetAllPetsByClientIdResource resource) {
        try {
            var query = GetAllPetsByClientIdQueryFromResourceAssembler.toQueryFromResource(resource);

            var pets = petQueryService.handle(query);

            var petsResource = pets.stream().map(PetResourceFromEntityAssembler::toResourceFromEntity);

            return ResponseEntity.ok(petsResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> deletePetById(@RequestBody DeletePetResource resource) {
        try {
            var command = DeletePetCommandFromResourceAssembler.toCommandFromResource(resource);
            petCommandService.handle(command);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}