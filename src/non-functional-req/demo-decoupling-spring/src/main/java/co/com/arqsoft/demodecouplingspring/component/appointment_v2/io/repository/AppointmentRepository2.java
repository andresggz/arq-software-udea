package co.com.arqsoft.demodecouplingspring.component.appointment_v2.io.repository;

import co.com.arqsoft.demodecouplingspring.component.appointment_v2.model.Appointment2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository2 extends JpaRepository<Appointment2, Long> {
}
