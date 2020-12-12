package co.com.arqsoft.demodecouplingspring.component.appointment_v3.io.web.v1.model;

import co.com.arqsoft.demodecouplingspring.component.appointment_v3.service.model.AppointmentSaveCmd;
import lombok.*;

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


    public static AppointmentSaveCmd toModel(AppointmentSaveRequest appointmentToCreate) {
        return AppointmentSaveCmd.builder().scheduledFor(appointmentToCreate.getScheduledFor())
                .build();
    }

}
