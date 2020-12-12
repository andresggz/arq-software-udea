package co.com.arqsoft.demodecouplingspring.component.appointment_v3.io.web.v1;

import co.com.arqsoft.demodecouplingspring.component.appointment_v3.io.web.v1.model.AppointmentSaveRequest;
import co.com.arqsoft.demodecouplingspring.component.appointment_v3.model.Appointment3;
import co.com.arqsoft.demodecouplingspring.component.appointment_v3.service.AppointmentService;
import co.com.arqsoft.demodecouplingspring.component.appointment_v3.service.model.AppointmentSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController(value = "c3")
@RequestMapping(path = "/api/v1/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AppointmentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Qualifier("s3")
    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Void> create(@Valid @NotNull @RequestBody AppointmentSaveRequest
                                               appointmentToCreate){
        logger.debug("Begin create: appointmentToCreate = {}", appointmentToCreate);

        final AppointmentSaveCmd appointmentToCreateCmd =
                AppointmentSaveRequest.toModel(appointmentToCreate);

        final Appointment3 appointmentCreated = appointmentService.create(appointmentToCreateCmd);

        URI location = fromUriString("/api/v1/appointments_v2").path("/{id}")
                .buildAndExpand(appointmentCreated.getId()).toUri();

        logger.debug("End create: appointmentCreated = {}", appointmentCreated);

        return ResponseEntity.created(location).build();
    }
}
