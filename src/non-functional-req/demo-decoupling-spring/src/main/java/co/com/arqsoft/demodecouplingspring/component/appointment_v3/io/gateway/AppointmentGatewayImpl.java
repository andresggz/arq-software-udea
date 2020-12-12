package co.com.arqsoft.demodecouplingspring.component.appointment_v3.io.gateway;

import co.com.arqsoft.demodecouplingspring.component.appointment_v3.io.repository.AppointmentRepository3;
import co.com.arqsoft.demodecouplingspring.component.appointment_v3.model.Appointment3;
import co.com.arqsoft.demodecouplingspring.component.appointment_v3.service.AppointmentGateway;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class AppointmentGatewayImpl implements AppointmentGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AppointmentRepository3 appointmentRepository;

    @Override
    public Appointment3 save(@NotNull Appointment3 appointmentToCreate) {
        logger.debug("Begin save: appointmentToCreate = {}", appointmentToCreate);

        final Appointment3 appointmentToBeCreated = appointmentToCreate.toBuilder()
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .attended(false)
                .build();

        final Appointment3 appointmentCreated = appointmentRepository.save(appointmentToBeCreated);

        logger.debug("End save: appointmentCreated = {}", appointmentCreated);

        return appointmentCreated;
    }
}
