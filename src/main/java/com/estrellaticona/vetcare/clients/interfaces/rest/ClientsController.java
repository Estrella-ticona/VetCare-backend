package com.estrellaticona.vetcare.clients.interfaces.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estrellaticona.vetcare.clients.domain.model.queries.GetAllClientsQuery;
import com.estrellaticona.vetcare.clients.domain.services.ClientCommandService;
import com.estrellaticona.vetcare.clients.domain.services.ClientQueryService;
import com.estrellaticona.vetcare.clients.interfaces.rest.resources.CreateClientResource;
import com.estrellaticona.vetcare.clients.interfaces.rest.transform.ClientResourceFromEntityAssembler;
import com.estrellaticona.vetcare.clients.interfaces.rest.transform.CreateClientCommandFromResourceAssembler;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    @Autowired
    private ClientCommandService clientCommandService;

    @Autowired
    private ClientQueryService clientQueryService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping
    public ResponseEntity<Object> createClient(@RequestBody CreateClientResource resource) {
        try {
            var doctorId = ((Long) request.getAttribute("userId"));

            var command = CreateClientCommandFromResourceAssembler.toCommandFromResource(doctorId, resource);

            var client = clientCommandService.handle(command);

            var clientResource = ClientResourceFromEntityAssembler.toResourceFromEntity(client);

            return ResponseEntity.ok(clientResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Object> getClients() {
        try {
            var query = new GetAllClientsQuery();

            var clients = clientQueryService.handle(query);

            var clientsResource = clients.stream().map(ClientResourceFromEntityAssembler::toResourceFromEntity);

            return ResponseEntity.ok(clientsResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}