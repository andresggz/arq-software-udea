package co.com.arqsoft.demodecouplingspring.component.appointment_v3.io.repository;

import co.com.arqsoft.demodecouplingspring.component.appointment_v3.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
