package co.com.arqsoft.demodecouplingspring.component.appointment_v1.io.repository;

import co.com.arqsoft.demodecouplingspring.component.appointment_v1.model.Appointment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends PagingAndSortingRepository<Appointment, Long> {
}
