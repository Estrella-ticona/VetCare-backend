package com.estrellaticona.vetcare.appointments.interfaces.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estrellaticona.vetcare.appointments.domain.model.queries.GetAllHistoriesQuery;
import com.estrellaticona.vetcare.appointments.domain.services.AppointmentCommandService;
import com.estrellaticona.vetcare.appointments.domain.services.AppointmentQueryService;
import com.estrellaticona.vetcare.appointments.interfaces.rest.resources.GetHistoryByClientIdResource;
import com.estrellaticona.vetcare.appointments.interfaces.rest.resources.UpdateAppointmentByIdResource;
import com.estrellaticona.vetcare.appointments.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.estrellaticona.vetcare.appointments.interfaces.rest.transform.GetHistoryByClientIdQueryFromResourceAssembler;
import com.estrellaticona.vetcare.appointments.interfaces.rest.transform.HistoryResourceFromEntityAssembler;
import com.estrellaticona.vetcare.appointments.interfaces.rest.transform.UpdateAppointmentByIdCommandFromResourceAssembler;

@RestController
@RequestMapping("/histories")
public class HistoriesController {
    @Autowired
    private AppointmentCommandService appointmentCommandService;

    @Autowired
    private AppointmentQueryService appointmentQueryService;

    @GetMapping
    public ResponseEntity<Object> getAllHistories() {
        try {
            var query = new GetAllHistoriesQuery();
            var histories = appointmentQueryService.handle(query);

            var historiesResource = histories.stream().map(HistoryResourceFromEntityAssembler::toResourceFromEntity)
                    .toList();

            return ResponseEntity.ok(historiesResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/by-client-id")
    public ResponseEntity<Object> getHistoryByClientId(@RequestBody GetHistoryByClientIdResource resource) {
        try {
            var query = GetHistoryByClientIdQueryFromResourceAssembler.toQueryFromResource(resource);
            var history = appointmentQueryService.handle(query);

            var historyResource = HistoryResourceFromEntityAssembler.toResourceFromEntity(history);

            return ResponseEntity.ok(historyResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateAppointmentById(@RequestBody UpdateAppointmentByIdResource resource) {
        try {
            var command = UpdateAppointmentByIdCommandFromResourceAssembler.toCommandFromResource(resource);
            var history = appointmentCommandService.handle(command);

            var historyResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(history);

            return ResponseEntity.ok(historyResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}