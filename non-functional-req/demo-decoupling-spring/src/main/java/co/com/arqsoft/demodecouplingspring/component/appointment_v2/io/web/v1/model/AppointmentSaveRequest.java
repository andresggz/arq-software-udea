package co.com.arqsoft.demodecouplingspring.component.appointment_v2.io.web.v1.model;

import co.com.arqsoft.demodecouplingspring.component.appointment_v2.model.Appointment;
import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentSaveRequest {

    @NotNull
    private LocalDateTime scheduledFor;

    @NotNull
    private Long doctorId;

    @NotNull
    private Long patientId;


    public static Appointment toModel(AppointmentSaveRequest appointmentToCreate){
        return Appointment.builder().scheduledFor(appointmentToCreate.getScheduledFor())
                .build();
    }
}
