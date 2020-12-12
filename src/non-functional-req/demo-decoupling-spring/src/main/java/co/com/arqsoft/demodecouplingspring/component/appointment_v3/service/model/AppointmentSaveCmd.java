package co.com.arqsoft.demodecouplingspring.component.appointment_v3.service.model;

import co.com.arqsoft.demodecouplingspring.component.appointment_v3.model.Appointment3;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentSaveCmd {

    @NotNull
    private LocalDateTime scheduledFor;

    @NotNull
    private Long doctorId;

    @NotNull
    private Long patientId;


    public static Appointment3 toModel(AppointmentSaveCmd appointmentToCreate) {
        return Appointment3.builder().scheduledFor(appointmentToCreate.getScheduledFor())
                .build();
    }
}
