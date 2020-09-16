package co.com.arqsoft.demodecouplingspring.component.appointment_v3.service;

import co.com.arqsoft.demodecouplingspring.component.appointment_v3.model.Appointment;

import javax.validation.constraints.NotNull;

public interface AppointmentGateway {

    Appointment save(@NotNull Appointment appointmentToCreate);
}
