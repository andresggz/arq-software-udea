package co.com.arqsoft.demodecouplingspring.component.appointment_v1.io.web;

import co.com.arqsoft.demodecouplingspring.component.appointment_v1.model.Appointment;
import co.com.arqsoft.demodecouplingspring.component.appointment_v1.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AppointmentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment> create(@Valid @NotNull @RequestBody Appointment appointment){
        logger.debug("Begin create: appointment = {}", appointment);

        final Appointment appointment2 = appointmentService.create(appointment);

        logger.debug("End create: appointment = {}", appointment2);
        return ResponseEntity.ok(appointment2);
    }
}
