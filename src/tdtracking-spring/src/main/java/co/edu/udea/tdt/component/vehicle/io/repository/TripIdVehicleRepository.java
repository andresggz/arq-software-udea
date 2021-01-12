package co.edu.udea.tdt.component.vehicle.io.repository;

import co.edu.udea.tdt.component.vehicle.model.TripIdVehicle;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripIdVehicleRepository extends PagingAndSortingRepository<TripIdVehicle, Long> {
}
