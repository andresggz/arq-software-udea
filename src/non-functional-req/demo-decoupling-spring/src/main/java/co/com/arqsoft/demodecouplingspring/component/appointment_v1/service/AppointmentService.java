package co.com.arqsoft.demodecouplingspring.component.appointment_v1.service;

import co.com.arqsoft.demodecouplingspring.component.appointment_v1.io.repository.AppointmentRepository1;
import co.com.arqsoft.demodecouplingspring.component.appointment_v1.model.Appointment1;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value = "s1")
@Transactional
@RequiredArgsConstructor
public class AppointmentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AppointmentRepository1 appointmentRepository;

    public Appointment1 create(Appointment1 appointment){
        logger.debug("Begin create: appointment = {}", appointment);

        final Appointment1 appointment2 = appointmentRepository.save(appointment);

        logger.debug("End create: appointment2 = {}", appointment2);
        return appointment2;
    }
}
