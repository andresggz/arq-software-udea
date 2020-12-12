package co.com.arqsoft.demodecouplingspring.component.appointment_v3.service;

import co.com.arqsoft.demodecouplingspring.component.appointment_v3.model.Appointment3;

import javax.validation.constraints.NotNull;

public interface AppointmentGateway {

    Appointment3 save(@NotNull Appointment3 appointmentToCreate);
}
