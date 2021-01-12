package co.edu.udea.tdt.component.trip.io.repository;

import co.edu.udea.tdt.component.trip.model.Trip;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends PagingAndSortingRepository<Trip, Long>, JpaSpecificationExecutor<Trip>{
}
