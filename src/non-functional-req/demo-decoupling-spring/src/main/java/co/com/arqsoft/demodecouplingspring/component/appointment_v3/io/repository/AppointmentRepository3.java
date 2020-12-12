package co.com.arqsoft.demodecouplingspring.component.appointment_v3.io.repository;

import co.com.arqsoft.demodecouplingspring.component.appointment_v3.model.Appointment3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository3 extends JpaRepository<Appointment3, Long> {
}
