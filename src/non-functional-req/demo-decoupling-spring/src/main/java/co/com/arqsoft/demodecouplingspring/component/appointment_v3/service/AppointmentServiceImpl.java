package co.com.arqsoft.demodecouplingspring.component.appointment_v3.service;

import co.com.arqsoft.demodecouplingspring.component.appointment_v3.model.Appointment3;
import co.com.arqsoft.demodecouplingspring.component.appointment_v3.service.model.AppointmentSaveCmd;
import co.com.arqsoft.demodecouplingspring.component.shared.web.exception.ConflictException;
import co.com.arqsoft.demodecouplingspring.component.user.model.Role;
import co.com.arqsoft.demodecouplingspring.component.user.model.User;
import co.com.arqsoft.demodecouplingspring.component.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service(value = "s3")
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl implements AppointmentService{

    private static final String USER_DOES_NOT_HAVE_DOCTOR_ROLE = "User does not have DOCTOR Role. userId = ";

    private static final String USER_DOES_NOT_HAVE_PATIENT_ROLE = "User does not have PATIENT Role. userId = ";


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    private final AppointmentGateway appointmentGateway;

    @Override
    public Appointment3 create(@NotNull AppointmentSaveCmd appointmentToCreateCmd) {
        logger.debug("Begin create: appointmentToCreateCmd = {}", appointmentToCreateCmd);

        final Appointment3 appointmentToCreate = AppointmentSaveCmd.toModel(appointmentToCreateCmd);

        final User doctor = userService.findById(appointmentToCreateCmd.getDoctorId());

        isDoctor(doctor);

        final User patient = userService.findById(appointmentToCreateCmd.getPatientId());

        isPatient(patient);

        appointmentToCreate.toBuilder()
                //.doctor(doctor)
                //.patient(patient)
                .build();

        final Appointment3 appointmentCreated = appointmentGateway.save(appointmentToCreate);

        logger.debug("End create: appointmentCreated = {}", appointmentCreated);
        return appointmentCreated;
    }

    private boolean isDoctor(User doctor){
        if(!doctor.getRole().equals(Role.DOCTOR)){
            throw new ConflictException(USER_DOES_NOT_HAVE_DOCTOR_ROLE + doctor.getId());
        }

        return true;
    }

    private boolean isPatient(User patient){
        if(!patient.getRole().equals(Role.PATIENT)){
            throw new ConflictException(USER_DOES_NOT_HAVE_PATIENT_ROLE + patient.getId());
        }

        return true;
    }
}
