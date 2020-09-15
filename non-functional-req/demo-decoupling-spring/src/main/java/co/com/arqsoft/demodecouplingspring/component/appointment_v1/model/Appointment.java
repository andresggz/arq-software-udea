package co.com.arqsoft.demodecouplingspring.component.appointment_v1.model;

import co.com.arqsoft.demodecouplingspring.component.user.model.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments_v1")
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Appointment {

    @Id
    private Long id;

    @NotNull
    private LocalDateTime scheduledFor;

    @NotNull
    private Boolean attended;

    @NotNull
    private LocalDateTime createDate;

    @NotNull
    private LocalDateTime updateDate;

}
