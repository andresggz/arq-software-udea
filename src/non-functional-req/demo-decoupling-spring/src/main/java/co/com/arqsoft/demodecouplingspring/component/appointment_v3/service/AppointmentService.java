package co.com.arqsoft.demodecouplingspring.component.appointment_v3.service;

import co.com.arqsoft.demodecouplingspring.component.appointment_v3.model.Appointment;
import co.com.arqsoft.demodecouplingspring.component.appointment_v3.service.model.AppointmentSaveCmd;

import javax.validation.constraints.NotNull;

public interface AppointmentService {

    Appointment create(@NotNull AppointmentSaveCmd appointmentToCreateCmd);
}
