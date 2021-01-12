package co.edu.udea.tdt.component.fleet.io.repository;

import co.edu.udea.tdt.component.fleet.model.Fleet;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FleetRepository extends PagingAndSortingRepository<Fleet, Long>, JpaSpecificationExecutor<Fleet>{
}
