package com.estrellaticona.vetcare.appointments.interfaces.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estrellaticona.vetcare.appointments.domain.model.queries.GetAllAppointmentsQuery;
import com.estrellaticona.vetcare.appointments.domain.services.AppointmentCommandService;
import com.estrellaticona.vetcare.appointments.domain.services.AppointmentQueryService;
import com.estrellaticona.vetcare.appointments.interfaces.rest.resources.CreateAppointmentResource;
import com.estrellaticona.vetcare.appointments.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.estrellaticona.vetcare.appointments.interfaces.rest.transform.CreateAppointmentCommandFromResourceAssembler;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {
    @Autowired
    private AppointmentCommandService appointmentCommandService;

    @Autowired
    private AppointmentQueryService appointmentQueryService;

    @PostMapping
    public ResponseEntity<Object> createAppointment(@RequestBody CreateAppointmentResource resource) {
        try {
            var command = CreateAppointmentCommandFromResourceAssembler.toCommandFromResource(resource);
            var appointment = appointmentCommandService.handle(command);

            var appointmentResource = AppointmentResourceFromEntityAssembler.toResourceFromEntity(appointment);

            return ResponseEntity.ok(appointmentResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllAppointmentsResource() {
        try {
            var query = new GetAllAppointmentsQuery();
            var appointments = appointmentQueryService.handle(query);

            var appointmentsResource = appointments.stream()
                    .map(AppointmentResourceFromEntityAssembler::toResourceFromEntity)
                    .toList();

            return ResponseEntity.ok(appointmentsResource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

}