package co.com.arqsoft.demodecouplingspring.component.appointment_v1.io.repository;

import co.com.arqsoft.demodecouplingspring.component.appointment_v1.model.Appointment1;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository1 extends PagingAndSortingRepository<Appointment1, Long> {
}
