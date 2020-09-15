package co.com.arqsoft.demodecouplingspring.component.appointment_v2.service;

import co.com.arqsoft.demodecouplingspring.component.appointment_v2.io.repository.AppointmentRepository;
import co.com.arqsoft.demodecouplingspring.component.appointment_v2.io.web.v1.model.AppointmentSaveRequest;
import co.com.arqsoft.demodecouplingspring.component.appointment_v2.model.Appointment;
import co.com.arqsoft.demodecouplingspring.component.shared.web.exception.ConflictException;
import co.com.arqsoft.demodecouplingspring.component.user.model.Role;
import co.com.arqsoft.demodecouplingspring.component.user.model.User;
import co.com.arqsoft.demodecouplingspring.component.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    private final AppointmentRepository appointmentRepository;

    public Appointment create(AppointmentSaveRequest appointmentSaveRequest){
        logger.debug("Begin create: appointmentSaveRequest = {}", appointmentSaveRequest);

        final Appointment appointmentToCreate = AppointmentSaveRequest.toModel(appointmentSaveRequest);

        final User doctor = userService.findById(appointmentSaveRequest.getDoctorId());

        if(!doctor.getRole().equals(Role.DOCTOR)){
            throw new ConflictException("User does not have DOCTOR Role. userId = " + doctor.getId());
        }

        final User patient = userService.findById(appointmentSaveRequest.getPatientId());

        if(!patient.getRole().equals(Role.PATIENT)){
            throw new ConflictException("User does not have PATIENT Role. userId = " + patient.getId());
        }

        appointmentToCreate.toBuilder().doctor(doctor)
                .patient(patient)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .attended(false)
                .build();

        final Appointment appointmentCreated = appointmentRepository.save(appointmentToCreate);

        logger.debug("End create: appointmentCreated = {}", appointmentCreated);
        return appointmentCreated;
    }


}
