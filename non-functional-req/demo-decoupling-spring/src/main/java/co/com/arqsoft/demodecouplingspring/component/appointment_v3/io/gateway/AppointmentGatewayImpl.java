package co.com.arqsoft.demodecouplingspring.component.appointment_v3.io.gateway;

import co.com.arqsoft.demodecouplingspring.component.appointment_v3.io.repository.AppointmentRepository;
import co.com.arqsoft.demodecouplingspring.component.appointment_v3.model.Appointment;
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

    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment save(@NotNull Appointment appointmentToCreate) {
        logger.debug("Begin save: appointmentToCreate = {}", appointmentToCreate);

        final Appointment appointmentToBeCreated = appointmentToCreate.toBuilder()
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .attended(false)
                .build();

        final Appointment appointmentCreated = appointmentRepository.save(appointmentToBeCreated);

        logger.debug("End save: appointmentCreated = {}", appointmentCreated);

        return appointmentCreated;
    }
}
