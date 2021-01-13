package co.edu.udea.tdt.component.vehicle.io.repository;

import co.edu.udea.tdt.component.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle>{
}
