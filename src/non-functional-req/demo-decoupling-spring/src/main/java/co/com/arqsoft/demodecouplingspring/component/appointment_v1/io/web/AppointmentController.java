package co.com.arqsoft.demodecouplingspring.component.appointment_v1.io.web;

import co.com.arqsoft.demodecouplingspring.component.appointment_v1.model.Appointment1;
import co.com.arqsoft.demodecouplingspring.component.appointment_v1.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController(value = "c1")
@RequestMapping(path = "/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AppointmentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Qualifier("s1")
    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment1> create(@Valid @NotNull @RequestBody Appointment1 appointment){
        logger.debug("Begin create: appointment = {}", appointment);

        final Appointment1 appointment2 = appointmentService.create(appointment);

        logger.debug("End create: appointment = {}", appointment2);
        return ResponseEntity.ok(appointment2);
    }
}
