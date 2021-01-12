package co.edu.udea.tdt.component.fleet.io.repository;

import co.edu.udea.tdt.component.fleet.model.VehicleIdFleet;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleIdFleetRepository extends PagingAndSortingRepository<VehicleIdFleet, Long> {
}
