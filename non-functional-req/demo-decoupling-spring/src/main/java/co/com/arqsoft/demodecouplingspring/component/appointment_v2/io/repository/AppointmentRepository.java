package co.com.arqsoft.demodecouplingspring.component.appointment_v2.io.repository;

import co.com.arqsoft.demodecouplingspring.component.appointment_v2.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
