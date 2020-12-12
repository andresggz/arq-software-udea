package co.com.arqsoft.demodecouplingspring.component.appointment_v3.service;

import co.com.arqsoft.demodecouplingspring.component.appointment_v3.model.Appointment3;
import co.com.arqsoft.demodecouplingspring.component.appointment_v3.service.model.AppointmentSaveCmd;

import javax.validation.constraints.NotNull;

public interface AppointmentService {

    Appointment3 create(@NotNull AppointmentSaveCmd appointmentToCreateCmd);
}
