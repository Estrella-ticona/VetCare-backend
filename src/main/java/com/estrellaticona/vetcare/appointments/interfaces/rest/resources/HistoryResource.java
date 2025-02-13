package com.estrellaticona.vetcare.appointments.interfaces.rest.resources;

import java.util.List;

public record HistoryResource(List<AppointmentResource> appointments) {
}